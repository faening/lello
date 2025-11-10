package io.github.faening.lello.feature.journal.medication

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.designsystem.media.LelloMedia
import io.github.faening.lello.core.domain.usecase.journal.medication.GetRegisteredDosagesForTodayUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.SaveMedicationJournalUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationsUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.GetAllMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillisOnDate
import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class MedicationJournalViewModel @Inject constructor(
    private val getAllMedicationsUseCase: GetAllMedicationsUseCase,
    private val getAllMedicationSkipReasonOptionUseCase: GetAllMedicationSkipReasonOptionUseCase,
    private val getRegisteredDosagesForTodayUseCase: GetRegisteredDosagesForTodayUseCase,
    private val saveMedicationJournalUseCase: SaveMedicationJournalUseCase
) : ViewModel() {

    private val _medications = MutableStateFlow<List<Medication>>(emptyList())
    val medications: StateFlow<List<Medication>> = _medications.asStateFlow()

    private val _registeredDosageIds = MutableStateFlow<Set<Long>>(emptySet())
    val registeredDosageIds: StateFlow<Set<Long>> = _registeredDosageIds.asStateFlow()

    private val _skipReasonOptions = MutableStateFlow<List<MedicationSkipReasonOption>>(emptyList())
    val skipReasonOptions: StateFlow<List<MedicationSkipReasonOption>> = _skipReasonOptions

    private val _selectedMedication = MutableStateFlow<Medication?>(null)
    val selectedMedication: StateFlow<Medication?> = _selectedMedication.asStateFlow()

    private val _selectedDosageIndex = MutableStateFlow<Int?>(null)
    val selectedDosageIndex: StateFlow<Int?> = _selectedDosageIndex.asStateFlow()

    private var _exoPlayer: ExoPlayer? = null
    val exoPlayer: ExoPlayer? get() = _exoPlayer

    init {
        loadMedications()
        loadSkipReasonOptions()
    }

    private fun loadMedications() {
        viewModelScope.launch {
            getAllMedicationsUseCase.invoke().collect { medicationList ->
                _medications.value = medicationList
                loadRegisteredDosages(medicationList)
            }
        }
    }

    private fun loadRegisteredDosages(medications: List<Medication>) {
        viewModelScope.launch {
            val registeredIds = mutableSetOf<Long>()

            medications.forEach { medication ->
                medication.id?.let { medicationId ->
                    val dosageIds = getRegisteredDosagesForTodayUseCase.invoke(medicationId)
                    registeredIds.addAll(dosageIds)
                }
            }

            _registeredDosageIds.value = registeredIds
        }
    }

    private fun loadSkipReasonOptions() {
        viewModelScope.launch {
            getAllMedicationSkipReasonOptionUseCase.invoke().collect { _skipReasonOptions.value = it }
        }
    }

    fun setSelectedDosage(medication: Medication, dosageIndex: Int) {
        _selectedMedication.value = medication
        _selectedDosageIndex.value = dosageIndex
    }

    fun saveMedicationJournal(taken: Boolean, skipReasonOption: MedicationSkipReasonOption?) {
        viewModelScope.launch {
            val medication = _selectedMedication.value ?: return@launch
            val dosageIndex = _selectedDosageIndex.value ?: return@launch

            // Recarregar o medicamento do banco de dados para obter os IDs corretos
            val updatedMedication = _medications.value.find { it.id == medication.id } ?: return@launch
            val dosage = updatedMedication.dosages.getOrNull(dosageIndex) ?: return@launch

            // Verificar se a dosagem tem um ID válido
            if (dosage.id == null || dosage.id == 0L) {
                Log.d("Test", "Dosage ID is invalid: ${dosage.id}")
                return@launch
            }

            val currentTime = System.currentTimeMillis()
            val schedulingTime = dosage.time.toEpochMillisOnDate(
                date = LocalDate.now(),
                zone = ZoneId.systemDefault()
            )

            val medicationJournal = MedicationJournal(
                id = null,
                medication = updatedMedication,
                medicationDosage = dosage,
                scheduledTime = schedulingTime,
                taken = taken,
                skipReasonOption = skipReasonOption,
                registeredAt = currentTime,
                createdAt = currentTime
            )

            saveMedicationJournalUseCase.invoke(medicationJournal)

            dosage.id?.let { dosageId ->
                _registeredDosageIds.value += dosageId
            }
        }
    }

    /**
     * Prepara o ExoPlayer com o vídeo correspondente ao humor atual.
     *
     * @param context Contexto necessário para a criação do ExoPlayer.
     */
    fun prepareVideo(context: Context) {
        val video = LelloMedia.Video.JournalSummaryBackgroundYellow
        _exoPlayer?.release()
        _exoPlayer = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${video.resId}")
            setMediaItem(mediaItem)
            repeatMode = Player.REPEAT_MODE_ALL
            volume = 0f
            prepare()
        }
    }
}