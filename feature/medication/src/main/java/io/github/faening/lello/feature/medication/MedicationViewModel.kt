package io.github.faening.lello.feature.medication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.medication.activeingredient.GetAllMedicationActiveIngredientOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.medication.dosageform.GetAllMedicationDosageFormOptionUseCase
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationViewModel @Inject constructor(
    // Options
    private val getAllActiveIngredientOptionUseCase: GetAllMedicationActiveIngredientOptionUseCase,
    private val getAllDosageFormOptionUseCase: GetAllMedicationDosageFormOptionUseCase,
    ): ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _allActiveIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())

    private val _activeIngredients = MutableStateFlow<List<MedicationActiveIngredientOption>>(emptyList())
    val activeIngredients: StateFlow<List<MedicationActiveIngredientOption>> = _activeIngredients

    private val _dosageFormOptions = MutableStateFlow<List<MedicationDosageFormOption>>(emptyList())
    val dosageFormOptions: StateFlow<List<MedicationDosageFormOption>> = _dosageFormOptions

    private val _selectedActiveIngredient = MutableStateFlow<MedicationActiveIngredientOption?>(null)


    init {
        loadActiveIngredients()
        loadDosageForms()
        setupSearch()
    }

    private fun loadActiveIngredients() {
        viewModelScope.launch {
            getAllActiveIngredientOptionUseCase.invoke().collect { ingredients ->
                _allActiveIngredients.value = ingredients
                _activeIngredients.value = ingredients
            }
        }
    }

    private fun loadDosageForms() {
        viewModelScope.launch {
            getAllDosageFormOptionUseCase.invoke().collect { _dosageFormOptions.value = it }
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
        filterActiveIngredients()
    }

    /**
     * Filtra a lista de ingredientes ativos conforme o texto de busca. Se o texto estiver vazio, exibe todos os
     * ingredientes.
     */
    private fun filterActiveIngredients() {
        val query = _searchQuery.value.trim().lowercase()
        _activeIngredients.value = if (query.isEmpty()) {
            _allActiveIngredients.value
        } else {
            _allActiveIngredients.value.filter { ingredient ->
                ingredient.description.lowercase().contains(query)
            }
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
     * Seleciona um ingrediente ativo da lista.
     *
     * @param option Ingrediente ativo selecionado.
     */
    fun selectActiveIngredient(option: MedicationActiveIngredientOption) {
        // Armazene o ingrediente selecionado (exemplo usando um StateFlow)
        _selectedActiveIngredient.value = option
    }
}