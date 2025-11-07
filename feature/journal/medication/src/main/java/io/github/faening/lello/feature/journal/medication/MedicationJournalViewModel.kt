package io.github.faening.lello.feature.journal.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.journal.medication.SaveMedicationJournalUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationsUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.GetAllMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillisOnDate
import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.medication.MedicationDosage
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
    private val saveMedicationJournalUseCase: SaveMedicationJournalUseCase
) : ViewModel() {

    private val _medications = MutableStateFlow<List<Medication>>(emptyList())
    val medications: StateFlow<List<Medication>> = _medications.asStateFlow()

    private val _skipReasonOptions = MutableStateFlow<List<MedicationSkipReasonOption>>(emptyList())
    val skipReasonOptions: StateFlow<List<MedicationSkipReasonOption>> = _skipReasonOptions

    private val _selectedMedication = MutableStateFlow<Medication?>(null)
    val selectedMedication: StateFlow<Medication?> = _selectedMedication.asStateFlow()

    private val _selectedDosageIndex = MutableStateFlow<Int?>(null)
    val selectedDosageIndex: StateFlow<Int?> = _selectedDosageIndex.asStateFlow()

    init {
        loadMedications()
        loadSkipReasonOptions()
    }

    private fun loadMedications() {
        viewModelScope.launch {
            getAllMedicationsUseCase.invoke().collect { _medications.value = it }
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

    fun clearSelectedDosage() {
        _selectedMedication.value = null
        _selectedDosageIndex.value = null
    }

    fun saveMedicationJournal(taken: Boolean, skipReasonOption: MedicationSkipReasonOption?) {
        viewModelScope.launch {
            val medication = _selectedMedication.value ?: return@launch
            val dosageIndex = _selectedDosageIndex.value ?: return@launch
            val dosage = medication.dosages.getOrNull(dosageIndex) ?: return@launch

            val currentTime = System.currentTimeMillis()
            val schedulingTime = dosage.time.toEpochMillisOnDate(
                date = LocalDate.now(),
                zone = ZoneId.systemDefault()
            )

            val medicationJournal = MedicationJournal(
                id = null,
                medication = medication,
                medicationDosage = dosage,
                scheduledTime = schedulingTime,
                taken = taken,
                skipReasonOption = skipReasonOption,
                registeredAt = currentTime,
                createdAt = currentTime
            )

            saveMedicationJournalUseCase.invoke(medicationJournal)
        }
    }
}