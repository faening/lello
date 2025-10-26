package io.github.faening.lello.feature.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.medication.GetAnvisaMedicationByProductNameUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetAnvisaMedicationByTherapeuticClassUseCase
import io.github.faening.lello.core.domain.usecase.options.dosageform.GetAllDosageFormOptionUseCase
import io.github.faening.lello.core.model.option.DosageFormOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    private val getAllDosageFormOptionUseCase: GetAllDosageFormOptionUseCase,
    private val getAnvisaMedicationByProductNameUseCase: GetAnvisaMedicationByProductNameUseCase,
    private val getAnvisaMedicationByTherapeuticClassUseCase: GetAnvisaMedicationByTherapeuticClassUseCase
): ViewModel() {

    private val _dosageFormOptions = MutableStateFlow<List<DosageFormOption>>(emptyList())
    val dosageFormOptions: StateFlow<List<DosageFormOption>> = _dosageFormOptions

    init {
        loadDosageForms()
    }

    private fun loadDosageForms() {
        viewModelScope.launch {
            getAllDosageFormOptionUseCase().collect { _dosageFormOptions.value = it }
        }
    }

}