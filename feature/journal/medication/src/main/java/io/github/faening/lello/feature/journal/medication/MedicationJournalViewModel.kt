package io.github.faening.lello.feature.journal.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.skipreason.GetAllMedicationSkipReasonOptionUseCase
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationJournalViewModel @Inject constructor(
    private val getAllMedicationUseCase: GetAllMedicationUseCase,
    private val getAllMedicationSkipReasonOptionUseCase: GetAllMedicationSkipReasonOptionUseCase,
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
            getAllMedicationUseCase.invoke().collect { _medications.value = it }
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
}