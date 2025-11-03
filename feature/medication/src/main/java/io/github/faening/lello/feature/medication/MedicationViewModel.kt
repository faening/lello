package io.github.faening.lello.feature.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.medication.DisableMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.SaveMedicationUseCase
import io.github.faening.lello.core.domain.usecase.medication.UpdateMedicationUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetAllMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetAllMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetAllMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    private val getAllActiveIngredientOptionUseCase: GetAllMedicationActiveIngredientOptionUseCase,
    private val getAllMedicationDosageUnitOptionUseCase: GetAllMedicationDosageUnitOptionUseCase,
    private val getAllDosageFormOptionUseCase: GetAllMedicationDosageFormOptionUseCase,
    private val getAllMedicationUseCase: GetAllMedicationUseCase,
    private val saveMedicationUseCase: SaveMedicationUseCase,
    private val updateMedicationUseCase: UpdateMedicationUseCase,
    private val disableMedicationUseCase: DisableMedicationUseCase,
) : ViewModel() {

    private val _medications = MutableStateFlow<List<Medication>>(emptyList())
    val medications: StateFlow<List<Medication>> = _medications.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allActiveIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())
    private val _activeIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())
    val activeIngredients: StateFlow<List<MedicationActiveIngredientOption>> = _activeIngredients

    private val _dosageFormOptions = MutableStateFlow<List<MedicationDosageFormOption>>(emptyList())
    val dosageFormOptions: StateFlow<List<MedicationDosageFormOption>> = _dosageFormOptions

    private val _dosageUnitOptions = MutableStateFlow<List<MedicationDosageUnitOption>>(emptyList())
    val dosageUnitOptions: StateFlow<List<MedicationDosageUnitOption>> = _dosageUnitOptions

    private val _selectedActiveIngredient = MutableStateFlow<MedicationActiveIngredientOption?>(null)
    val selectedActiveIngredient: StateFlow<MedicationActiveIngredientOption?> = _selectedActiveIngredient

    private val _selectedDosageForm = MutableStateFlow<MedicationDosageFormOption?>(null)
    val selectedDosageForm: StateFlow<MedicationDosageFormOption?> = _selectedDosageForm

    private val _selectedDosageUnit = MutableStateFlow<MedicationDosageUnitOption?>(null)
    val selectedDosageUnit: StateFlow<MedicationDosageUnitOption?> = _selectedDosageUnit

    private val _selectedDosageQuantity = MutableStateFlow(0.0)
    private val _selectedDosageQuantityString = MutableStateFlow("")
    val selectedDosageQuantityString: StateFlow<String> = _selectedDosageQuantityString.asStateFlow()

    private val _selectedDosageTime = MutableStateFlow("22:00")
    val selectedDosageTime: StateFlow<String> = _selectedDosageTime.asStateFlow()

    /**
     * Lista de dosagens cadastradas temporariamente antes de salvar o medicamento
     */
    private val _stagedDosages = MutableStateFlow<List<MedicationDosage>>(emptyList())
    val stagedDosages: StateFlow<List<MedicationDosage>> = _stagedDosages.asStateFlow()

    private val _editingMedication = MutableStateFlow<Medication?>(null)
    val editingMedication: StateFlow<Medication?> = _editingMedication.asStateFlow()

    private val _editingDosageIndex = MutableStateFlow<Int?>(null)
    val editingDosageIndex: StateFlow<Int?> = _editingDosageIndex.asStateFlow()

    private val _hasChanges = MutableStateFlow(false)

    /**
     * Indica se a dosagem atual é válida para ser adicionada à lista de dosagens
     */
    val isDosageValid: StateFlow<Boolean> = combine(
        _selectedDosageQuantity,
        _selectedDosageUnit,
        _selectedDosageTime
    ) { quantity, unit, _ ->
        quantity > 0.0 && unit != null
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    /**
     * Indica se o medicamento atual é válido para ser salvo
     */
    val isMedicationValid: StateFlow<Boolean> = combine(
        _selectedActiveIngredient,
        _selectedDosageForm,
        _stagedDosages
    ) { activeIngredient, dosageForm, dosages ->
        activeIngredient != null && dosageForm != null && dosages.isNotEmpty()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val canSaveDosage: StateFlow<Boolean> = combine(
        isDosageValid,
        _hasChanges
    ) { isValid, hasChanges ->
        isValid && hasChanges
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    init {
        loadMedications()
        loadActiveIngredients()
        loadDosageUnits()
        loadDosageForms()
        setupSearch()
        setupSearchQueryObserver()
        setupDefaultDosageUnit()
    }

    // region: Loaders and setups

    private fun loadMedications() {
        viewModelScope.launch {
            getAllMedicationUseCase.invoke().collect { _medications.value = it }
        }
    }

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

    fun updateActiveIngredient(option: MedicationActiveIngredientOption) {
        _selectedActiveIngredient.value = option
    }

    fun updateDosageForm(option: MedicationDosageFormOption) {
        _selectedDosageForm.value = option
    }

    fun updateDosageUnit(option: MedicationDosageUnitOption?) {
        _selectedDosageUnit.value = option
        _hasChanges.value = true
    }

    fun updateDosageQuantity(newString: String) {
        if (newString.isEmpty()) {
            _selectedDosageQuantityString.value = ""
            _selectedDosageQuantity.value = 0.0
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
            _selectedDosageQuantityString.value = cleanedString
            _selectedDosageQuantity.value = cleanedString.toDoubleOrNull() ?: 0.0
        }

        _hasChanges.value = true
    }

    fun updateDosageTime(time: String) {
        _selectedDosageTime.value = time
        _hasChanges.value = true
    }

    fun saveStageDosage() {
        val quantity = _selectedDosageQuantity.value
        val unit = _selectedDosageUnit.value ?: return
        val timeString = _selectedDosageTime.value

        if (quantity <= 0.0) return

        try {
            val newDosage = MedicationDosage.fromViewModel(quantity, unit, timeString)
            val updatedList = (_stagedDosages.value + newDosage)
                .sortedBy { it.time }
                .mapIndexed { index, dosage ->
                    dosage.copy(dosageNumber = index + 1)
                }

            _stagedDosages.value = updatedList
            resetDosageFields()
        } catch (_: Exception) { }
    }

    fun startEditingDosage(medication: Medication, dosageIndex: Int) {
        _editingMedication.value = medication
        _editingDosageIndex.value = dosageIndex

        val dosage = medication.dosages.getOrNull(dosageIndex) ?: return

        _selectedDosageQuantity.value = dosage.quantity
        _selectedDosageQuantityString.value = dosage.quantity.toString()
        _selectedDosageUnit.value = dosage.unitOption
        _selectedDosageTime.value = dosage.time?.let {
            String.format("%02d:%02d", it.hour, it.minute)
        } ?: "22:00"

        _hasChanges.value = false
    }

    private fun resetDosageFields() {
        _selectedDosageQuantity.value = 0.0
        _selectedDosageQuantityString.value = ""
        _selectedDosageTime.value = "22:00"
    }

    fun removeDosage(dosage: MedicationDosage) {
        val updatedList = _stagedDosages.value
            .filter { it != dosage }
            .sortedBy { it.time }
            .mapIndexed { index, d ->
                d.copy(dosageNumber = index + 1)
            }
        _stagedDosages.value = updatedList
    }

    fun saveMedication() {
        viewModelScope.launch {
            try {
                val activeIngredient = _selectedActiveIngredient.value
                    ?: throw IllegalArgumentException("Princípio ativo não selecionado")

                val dosageForm = _selectedDosageForm.value
                    ?: throw IllegalArgumentException("Forma farmacêutica não selecionada")

                val now = System.currentTimeMillis()

                val medication = Medication(
                    id = null,
                    activeIngredientOption = activeIngredient,
                    dosageFormOption = dosageForm,
                    dosages = _stagedDosages.value,
                    active = true,
                    createdAt = now,
                    updatedAt = now
                )

                saveMedicationUseCase.invoke(medication)

                clearMedicationData()
            } catch (_: IllegalArgumentException) { }
        }
    }

    private fun clearMedicationData() {
        _searchQuery.value = ""
        _selectedActiveIngredient.value = null
        _selectedDosageForm.value = null
        _stagedDosages.value = emptyList()
        resetDosageFields()
    }

    fun updateMedicationDosage() {
        viewModelScope.launch {
            try {
                val medication = _editingMedication.value
                    ?: throw IllegalArgumentException("Nenhum medicamento em edição")

                val dosageIndex = _editingDosageIndex.value
                    ?: throw IllegalArgumentException("Nenhuma dosagem em edição")

                val quantity = _selectedDosageQuantity.value
                val unit = _selectedDosageUnit.value
                    ?: throw IllegalArgumentException("Unidade não selecionada")
                val timeString = _selectedDosageTime.value

                if (quantity <= 0.0) throw IllegalArgumentException("Quantidade inválida")

                val updatedDosage = MedicationDosage.fromViewModel(quantity, unit, timeString)
                    .copy(dosageNumber = dosageIndex + 1)

                val updatedDosages = medication.dosages.toMutableList().apply {
                    set(dosageIndex, updatedDosage)
                }

                val updatedMedication = medication.copy(
                    dosages = updatedDosages,
                    updatedAt = System.currentTimeMillis()
                )

                updateMedicationUseCase.invoke(updatedMedication)

                clearEditingState()
            } catch (_: Exception) { }
        }
    }

    private fun clearEditingState() {
        _editingMedication.value = null
        _editingDosageIndex.value = null
        _hasChanges.value = false
        resetDosageFields()
    }

    fun disableMedication(medication: Medication) {
        viewModelScope.launch {
            medication.id?.let { id ->
                disableMedicationUseCase.invoke(id)
            }
        }
    }

    // endregion: Setters and updaters
}