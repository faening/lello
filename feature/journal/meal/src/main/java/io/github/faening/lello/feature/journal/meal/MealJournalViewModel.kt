package io.github.faening.lello.feature.journal.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.meal.SaveMealJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.MealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.PortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAllAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetAllFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetAllLocationOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillis
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.MealOption
import io.github.faening.lello.core.model.option.PortionOption
import io.github.faening.lello.core.model.option.SocialOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MealJournalViewModel @Inject constructor(
    private val saveMealJournalUseCase: SaveMealJournalUseCase,
    private val rewardCalculatorService: RewardCalculatorService,
    // Options
    private val getAllAppetiteOptionUseCase: GetAllAppetiteOptionUseCase,
    private val getAllFoodOptionUseCase: GetAllFoodOptionUseCase,
    private val getAllLocationOptionUseCase: GetAllLocationOptionUseCase,
    mealOptionUseCase: MealOptionUseCase,
    portionOptionUseCase: PortionOptionUseCase,
    socialOptionUseCase: SocialOptionUseCase,
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

    private val _mealJournal = MutableStateFlow<MealJournal?>(null)

    private val _coinsAcquired = MutableStateFlow<Int>(50)
    val coinsAcquired: StateFlow<Int> = _coinsAcquired

    init {
        viewModelScope.launch {
            mealOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _mealOptions.value = it }
        }
        viewModelScope.launch {
            getAllAppetiteOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _appetiteOptions.value = it }
        }
        viewModelScope.launch {
            getAllFoodOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _foodOptions.value = it }
        }
        viewModelScope.launch {
            portionOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _portionOptions.value = it }
        }
        viewModelScope.launch {
            getAllLocationOptionUseCase.invoke()
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
        updateCoinsAcquired()
    }

    fun toggleMealSelection(description: String) {
        _mealOptions.update { list ->
            list.map { it.copy(selected = it.description == description) }
        }
    }

    fun toggleAppetiteSelection(description: String) {
        _appetiteOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleFoodSelection(description: String) {
        _foodOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun togglePortionSelection(description: String) {
        _portionOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleLocationSelection(description: String) {
        _locationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleSocialSelection(description: String) {
        _socialOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    private fun updateCoinsAcquired() {
        val mealournal = buildMealournal()
        val points = rewardCalculatorService.calculateForMealJournal(mealournal)
        _coinsAcquired.value = points
    }

    fun saveMealJournal() {
        if (_mealJournal.value == null) return
        viewModelScope.launch {
            val journal = buildMealournal()
            saveMealJournalUseCase.invoke(journal)
        }
    }

    private fun buildMealournal(): MealJournal {
        val millis = LocalDateTime.now().toEpochMillis()

        return MealJournal(
            mealTime = 0,
            createdAt = millis,
            mealOptions = mealOptions.value.filter { it.selected },
            appetiteOptions = appetiteOptions.value.filter { it.selected },
            foodOptions = foodOptions.value.filter { it.selected },
            portionOptions = portionOptions.value.filter { it.selected },
            locationOptions = locationOptions.value.filter { it.selected },
            socialOptions = socialOptions.value.filter { it.selected },
        ).also {
            _mealJournal.value = it
        }
    }
}