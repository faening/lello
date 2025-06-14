package io.github.faening.lello.feature.journal.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.AppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.FoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.MealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.PortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.model.journal.AppetiteOption
import io.github.faening.lello.core.model.journal.FoodOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.MealOption
import io.github.faening.lello.core.model.journal.PortionOption
import io.github.faening.lello.core.model.journal.SocialOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealJournalViewModel @Inject constructor(
    mealOptionUseCase: MealOptionUseCase,
    appetiteOptionUseCase: AppetiteOptionUseCase,
    foodOptionUseCase: FoodOptionUseCase,
    portionOptionUseCase: PortionOptionUseCase,
    locationOptionUseCase: LocationOptionUseCase,
    socialOptionUseCase: SocialOptionUseCase
) : ViewModel() {

    private val _mealTime = MutableStateFlow("")
    val mealTime: StateFlow<String> = _mealTime

    private val _mealOptions = MutableStateFlow<List<MealOption>>(emptyList())
    val mealOptions: StateFlow<List<MealOption>> = _mealOptions

    private val _appetiteOptions = MutableStateFlow<List<AppetiteOption>>(emptyList())
    val appetiteOptions: StateFlow<List<AppetiteOption>> = _appetiteOptions

    private val _foodOptions = MutableStateFlow<List<FoodOption>>(emptyList())
    val foodOptions: StateFlow<List<FoodOption>> = _foodOptions

    private val _portionOptions = MutableStateFlow<List<PortionOption>>(emptyList())
    val portionOptions: StateFlow<List<PortionOption>> = _portionOptions

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _socialOptions = MutableStateFlow<List<SocialOption>>(emptyList())
    val socialOptions: StateFlow<List<SocialOption>> = _socialOptions

    init {
        viewModelScope.launch {
            mealOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _mealOptions.value = it }
        }
        viewModelScope.launch {
            appetiteOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _appetiteOptions.value = it }
        }
        viewModelScope.launch {
            foodOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _foodOptions.value = it }
        }
        viewModelScope.launch {
            portionOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _portionOptions.value = it }
        }
        viewModelScope.launch {
            locationOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _locationOptions.value = it }
        }
        viewModelScope.launch {
            socialOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _socialOptions.value = it }
        }
    }
    
    fun updateMealTime(time: String) {
        _mealTime.value = time
    }

    fun toggleMealSelection(description: String) {
        _mealOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleAppetiteSelection(description: String) {
        _appetiteOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleFoodSelection(description: String) {
        _foodOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }
    fun togglePortionSelection(description: String) {
        _portionOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleLocationSelection(description: String) {
        _locationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleSocialSelection(description: String) {
        _socialOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }


}