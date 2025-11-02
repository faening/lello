package io.github.faening.lello.feature.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetAllMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetAllMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetAllMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    private val getAllActiveIngredientOptionUseCase: GetAllMedicationActiveIngredientOptionUseCase,
    private val getAllMedicationDosageUnitOptionUseCase: GetAllMedicationDosageUnitOptionUseCase,
    private val getAllDosageFormOptionUseCase: GetAllMedicationDosageFormOptionUseCase,
) : ViewModel() {

    // Loaded data and states
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allActiveIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())
    private val _activeIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())
    val activeIngredients: StateFlow<List<MedicationActiveIngredientOption>> = _activeIngredients

    private val _dosageFormOptions = MutableStateFlow<List<MedicationDosageFormOption>>(emptyList())
    val dosageFormOptions: StateFlow<List<MedicationDosageFormOption>> = _dosageFormOptions

    private val _dosageUnitOptions = MutableStateFlow<List<MedicationDosageUnitOption>>(emptyList())
    val dosageUnitOptions: StateFlow<List<MedicationDosageUnitOption>> = _dosageUnitOptions

    // Selected values
    private val _selectedActiveIngredient = MutableStateFlow<MedicationActiveIngredientOption?>(null)
    val selectedActiveIngredient: StateFlow<MedicationActiveIngredientOption?> = _selectedActiveIngredient

    private val _selectedDosageForm = MutableStateFlow<MedicationDosageFormOption?>(null)
    val selectedDosageForm: StateFlow<MedicationDosageFormOption?> = _selectedDosageForm

    private val _selectedDosageUnit = MutableStateFlow<MedicationDosageUnitOption?>(null)
    val selectedDosageUnit: StateFlow<MedicationDosageUnitOption?> = _selectedDosageUnit

    private val _dosageQuantity = MutableStateFlow(0.0)
    private val _dosageQuantityString = MutableStateFlow("")
    val dosageQuantityString: StateFlow<String> = _dosageQuantityString.asStateFlow()

    private val _selectedDosageTime = MutableStateFlow("22:00")
    val selectedDosageTime: StateFlow<String> = _selectedDosageTime.asStateFlow()

    private val _dosages = MutableStateFlow<List<MedicationDosage>>(emptyList())
    val dosages: StateFlow<List<MedicationDosage>> = _dosages.asStateFlow()

    init {
        loadActiveIngredients()
        loadDosageUnits()
        loadDosageForms()
        setupSearch()
        setupSearchQueryObserver()
        setupDefaultDosageUnit()
    }

    // region: Loaders and setups

    private fun loadActiveIngredients() {
        viewModelScope.launch {
            getAllActiveIngredientOptionUseCase.invoke().collect { _allActiveIngredients.value = it }
        }
    }

    private fun loadDosageForms() {
        viewModelScope.launch {
            getAllDosageFormOptionUseCase.invoke().collect { _dosageFormOptions.value = it }
        }
    }

    private fun loadDosageUnits() {
        viewModelScope.launch {
            getAllMedicationDosageUnitOptionUseCase.invoke().collect { _dosageUnitOptions.value = it }
        }
    }

    @OptIn(FlowPreview::class)
    private fun setupSearch() {
        viewModelScope.launch {
            _searchQuery.debounce(300).collect { filterActiveIngredients() }
        }
    }

    private fun filterActiveIngredients() {
        val query = _searchQuery.value.trim().lowercase()
        _activeIngredients.value = if (query.length >= 3) {
            _allActiveIngredients.value.filter { it.description.lowercase().contains(query) }
        } else {
            emptyList()
        }
    }

    private fun setupSearchQueryObserver() {
        viewModelScope.launch {
            _searchQuery.collect { query ->
                if (query.isEmpty()) _selectedActiveIngredient.value = null
                filterActiveIngredients()
            }
        }
    }

    private fun setupDefaultDosageUnit() {
        viewModelScope.launch {
            _dosageUnitOptions.collect { options ->
                if (options.isNotEmpty() && _selectedDosageUnit.value == null) {
                    val target = "miligrama (mg)"
                    val mgOption = options.firstOrNull { it.description.lowercase().contains(target) }
                    _selectedDosageUnit.value = mgOption ?: options.first()
                }
            }
        }
    }

    // endregion: Loaders and setups

    // region: Setters and updaters

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectActiveIngredient(option: MedicationActiveIngredientOption) {
        _selectedActiveIngredient.value = option
    }

    fun selectDosageForm(option: MedicationDosageFormOption) {
        _selectedDosageForm.value = option
    }

    fun selectDosageUnit(option: MedicationDosageUnitOption?) {
        _selectedDosageUnit.value = option
    }

    fun updateDosageQuantity(newString: String) {
        if (newString.isEmpty()) {
            _dosageQuantityString.value = ""
            _dosageQuantity.value = 0.0
            return
        }
        val normalizedString = newString.replace(',', '.')
        val regex = Regex("^\\d*\\.?\\d*$")
        if (normalizedString.matches(regex)) {
            val cleanedString = if (normalizedString.startsWith("0") &&
                normalizedString.length > 1 &&
                !normalizedString.startsWith("0.")
            ) {
                normalizedString.dropWhile { it == '0' }.ifEmpty { "0" }
            } else {
                normalizedString
            }
            _dosageQuantityString.value = cleanedString
            _dosageQuantity.value = cleanedString.toDoubleOrNull() ?: 0.0
        }
    }

    fun updateSelectedDosageTime(time: String) {
        _selectedDosageTime.value = time
    }

    fun saveDosage() {
        val quantity = _dosageQuantity.value
        val unit = _selectedDosageUnit.value?.description ?: return
        val timeString = _selectedDosageTime.value

        if (quantity <= 0.0) return

        try {
            val newDosage = MedicationDosage.fromViewModel(quantity, unit.lowercase(), timeString)
            val updatedList = (_dosages.value + newDosage)
                .sortedBy { it.time }
                .mapIndexed { index, dosage ->
                    dosage.copy(dosageNumber = index + 1)
                }

            _dosages.value = updatedList
            resetDosageFields()
        } catch (_: Exception) { }
    }

    private fun resetDosageFields() {
        _dosageQuantity.value = 0.0
        _dosageQuantityString.value = ""
        _selectedDosageTime.value = "22:00"
    }

    fun removeDosage(dosage: MedicationDosage) {
        val updatedList = _dosages.value
            .filter { it != dosage }
            .sortedBy { it.time }
            .mapIndexed { index, d ->
                d.copy(dosageNumber = index + 1)
            }
        _dosages.value = updatedList
    }

    // endregion: Setters and updaters
}