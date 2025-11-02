package io.github.faening.lello.feature.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetAllMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetAllMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageunit.GetAllMedicationDosageUnitOptionUseCase
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    // Options
    private val getAllActiveIngredientOptionUseCase: GetAllMedicationActiveIngredientOptionUseCase,
    private val getAllMedicationDosageUnitOptionUseCase: GetAllMedicationDosageUnitOptionUseCase,
    private val getAllDosageFormOptionUseCase: GetAllMedicationDosageFormOptionUseCase,
    ): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allActiveIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())

    private val _activeIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())
    val activeIngredients: StateFlow<List<MedicationActiveIngredientOption>> = _activeIngredients

    private val _dosageFormOptions = MutableStateFlow<List<MedicationDosageFormOption>>(emptyList())
    val dosageFormOptions: StateFlow<List<MedicationDosageFormOption>> = _dosageFormOptions

    private val _dosageQuantity = MutableStateFlow(0.0)

    private val _dosageQuantityString = MutableStateFlow("")
    val dosageQuantityString = _dosageQuantityString.asStateFlow()

    private val _dosageUnitOptions = MutableStateFlow<List<MedicationDosageUnitOption>>(emptyList())
    val dosageUnitOptions: StateFlow<List<MedicationDosageUnitOption>> = _dosageUnitOptions

    private val _selectedActiveIngredient = MutableStateFlow<MedicationActiveIngredientOption?>(null)
    val selectedActiveIngredient: StateFlow<MedicationActiveIngredientOption?> = _selectedActiveIngredient

    private val _selectedDosageForm = MutableStateFlow<MedicationDosageFormOption?>(null)
    val selectedDosageForm: StateFlow<MedicationDosageFormOption?> = _selectedDosageForm

    private val _selectedDosageUnit = MutableStateFlow<MedicationDosageUnitOption?>(null)
    val selectedDosageUnit: StateFlow<MedicationDosageUnitOption?> = _selectedDosageUnit

    private val _selectedDosageTime = MutableStateFlow<String>("22:00")
    val selectedDosageTime: StateFlow<String> = _selectedDosageTime.asStateFlow()

    init {
        loadActiveIngredients()
        loadDosageUnits()
        loadDosageForms()
        setupSearch()
        setupSearchQueryObserver()
    }

    /**
     * Carrega todos os ingredientes ativos disponíveis e atualiza o estado correspondente.
     */
    private fun loadActiveIngredients() {
        viewModelScope.launch {
            getAllActiveIngredientOptionUseCase.invoke().collect { _allActiveIngredients.value = it }
        }
    }

    /**
     * Carrega todas as formas de dosagem disponíveis e atualiza o estado correspondente.
     */
    private fun loadDosageForms() {
        viewModelScope.launch {
            getAllDosageFormOptionUseCase.invoke().collect { _dosageFormOptions.value = it }
        }
    }

    /**
     * Carrega todas as unidades de dosagem disponíveis e atualiza o estado correspondente.
     */
    private fun loadDosageUnits() {
        viewModelScope.launch {
            getAllMedicationDosageUnitOptionUseCase.invoke().collect { _dosageUnitOptions.value = it }
        }
    }

    /**
     * Atualiza o valor da busca de ingredientes ativos. Chama o filtro para atualizar a lista exibida conforme o
     * texto informado.
     *
     * @param query Texto digitado pelo usuário para busca.
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /**
     * Filtra a lista de ingredientes ativos conforme o texto de busca. Se o texto estiver vazio, exibe todos os
     * ingredientes.
     */
    private fun filterActiveIngredients() {
        val query = _searchQuery.value.trim().lowercase()
        _activeIngredients.value = if (query.length >= 3) {
            _allActiveIngredients.value.filter { ingredient ->
                ingredient.description.lowercase().contains(query)
            }
        } else {
            emptyList()
        }
    }

    /**
     * Carrega todos os ingredientes ativos disponíveis. Atualiza tanto a lista completa quanto a filtrada.
     */
    @OptIn(FlowPreview::class)
    private fun setupSearch() {
        // Opcional: se quiser debounce na busca
        viewModelScope.launch {
            _searchQuery.debounce(300).collect {
                filterActiveIngredients()
            }
        }
    }

    /**
     * Observa mudanças no texto de busca para limpar a seleção de ingrediente ativo quando o texto for apagado.
     */
    private fun setupSearchQueryObserver() {
        viewModelScope.launch {
            _searchQuery.collect { query ->
                if (query.isEmpty()) {
                    _selectedActiveIngredient.value = null
                }
                filterActiveIngredients()
            }
        }
    }

    /**
     * Seleciona um ingrediente ativo da lista.
     *
     * @param option Ingrediente ativo selecionado.
     */
    fun selectActiveIngredient(option: MedicationActiveIngredientOption) {
        _selectedActiveIngredient.value = option
    }

    /**
     * Seleciona uma forma de dosagem da lista.
     *
     * @param option Forma de dosagem selecionada.
     */
    fun selectDosageForm(option: MedicationDosageFormOption) {
        _selectedDosageForm.value = option
    }

    /**
     * Atualiza a quantidade de dosagem informada pelo usuário.
     *
     * @param quantity Quantidade de dosagem.
     */
    fun updateDosageQuantity(newString: String) {
        // Permite campo vazio
        if (newString.isEmpty()) {
            _dosageQuantityString.value = ""
            _dosageQuantity.value = 0.0
            return
        }

        // Regex para validar números decimais (permite . ou , como separador)
        val normalizedString = newString.replace(',', '.')
        val regex = Regex("^\\d*\\.?\\d*$")

        if (normalizedString.matches(regex)) {
            // Remove zeros à esquerda desnecessários (ex: "007" → "7", "0.5" mantém)
            val cleanedString = if (normalizedString.startsWith("0") &&
                normalizedString.length > 1 &&
                !normalizedString.startsWith("0.")) {
                normalizedString.dropWhile { it == '0' }.ifEmpty { "0" }
            } else {
                normalizedString
            }

            _dosageQuantityString.value = cleanedString
            _dosageQuantity.value = cleanedString.toDoubleOrNull() ?: 0.0
        }
    }

    /**
     * Seleciona uma unidade de dosagem da lista.
     *
     * @param option Unidade de dosagem selecionada.
     */
    fun selectDosageUnit(option: MedicationDosageUnitOption?) {
        _selectedDosageUnit.value = option
    }

    /**
     * Atualiza o horário de dosagem selecionado.
     *
     * @param time Horário selecionado no formato "HH:mm".
     */
    fun updateSelectedDosageTime(time: String) {
        _selectedDosageTime.value = time
    }

    fun getFormattedTime(): String? {
        return _selectedDosageTime.value.let { time ->
            try {
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                LocalTime.parse(time, formatter).format(formatter)
            } catch (e: Exception) {
                null
            }
        }
    }
}