package io.github.faening.lello.feature.journal.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.AppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.ClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.DosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.EmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.FoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.HealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.MealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.PortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.model.journal.AppetiteOption
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.DosageFormOption
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.core.model.journal.FoodOption
import io.github.faening.lello.core.model.journal.HealthOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.MealOption
import io.github.faening.lello.core.model.journal.PortionOption
import io.github.faening.lello.core.model.journal.SensationOption
import io.github.faening.lello.core.model.journal.SleepActivityOption
import io.github.faening.lello.core.model.journal.SleepQualityOption
import io.github.faening.lello.core.model.journal.SocialOption
import io.github.faening.lello.core.model.journal.JournalOption
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalSettingsViewModel @Inject constructor(
    private val emotionOptionUseCase: EmotionOptionUseCase,
    private val climateOptionUseCase: ClimateOptionUseCase,
    private val locationOptionUseCase: LocationOptionUseCase,
    private val socialOptionUseCase: SocialOptionUseCase,
    private val healthOptionUseCase: HealthOptionUseCase,
    private val appetiteOptionUseCase: AppetiteOptionUseCase,
    private val dosageFormOptionUseCase: DosageFormOptionUseCase,
    private val foodOptionUseCase: FoodOptionUseCase,
    private val mealOptionUseCase: MealOptionUseCase,
    private val portionOptionUseCase: PortionOptionUseCase,
    private val sensationOptionUseCase: SensationOptionUseCase,
    private val sleepActivityOptionUseCase: SleepActivityOptionUseCase,
    private val sleepQualityOptionUseCase: SleepQualityOptionUseCase,
) : ViewModel() {

    private val _emotionOptions = MutableStateFlow<List<EmotionOption>>(emptyList())
    val emotionOptions: StateFlow<List<EmotionOption>> = _emotionOptions

    private val _climateOptions = MutableStateFlow<List<ClimateOption>>(emptyList())
    val climateOptions: StateFlow<List<ClimateOption>> = _climateOptions

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _socialOptions = MutableStateFlow<List<SocialOption>>(emptyList())
    val socialOptions: StateFlow<List<SocialOption>> = _socialOptions

    private val _healthOptions = MutableStateFlow<List<HealthOption>>(emptyList())
    val healthOptions: StateFlow<List<HealthOption>> = _healthOptions

    private val _appetiteOptions = MutableStateFlow<List<AppetiteOption>>(emptyList())
    val appetiteOptions: StateFlow<List<AppetiteOption>> = _appetiteOptions

    private val _dosageFormOptions = MutableStateFlow<List<DosageFormOption>>(emptyList())
    val dosageFormOptions: StateFlow<List<DosageFormOption>> = _dosageFormOptions

    private val _foodOptions = MutableStateFlow<List<FoodOption>>(emptyList())
    val foodOptions: StateFlow<List<FoodOption>> = _foodOptions

    private val _mealOptions = MutableStateFlow<List<MealOption>>(emptyList())
    val mealOptions: StateFlow<List<MealOption>> = _mealOptions

    private val _portionOptions = MutableStateFlow<List<PortionOption>>(emptyList())
    val portionOptions: StateFlow<List<PortionOption>> = _portionOptions

    private val _sensationOptions = MutableStateFlow<List<SensationOption>>(emptyList())
    val sensationOptions: StateFlow<List<SensationOption>> = _sensationOptions

    private val _sleepActivityOptions = MutableStateFlow<List<SleepActivityOption>>(emptyList())
    val sleepActivityOptions: StateFlow<List<SleepActivityOption>> = _sleepActivityOptions

    private val _sleepQualityOptions = MutableStateFlow<List<SleepQualityOption>>(emptyList())
    val sleepQualityOptions: StateFlow<List<SleepQualityOption>> = _sleepQualityOptions

    init {
        viewModelScope.launch {
            emotionOptionUseCase.getAll().collect { _emotionOptions.value = it }
        }
        viewModelScope.launch {
            climateOptionUseCase.getAll().collect { _climateOptions.value = it }
        }
        viewModelScope.launch {
            locationOptionUseCase.getAll().collect { _locationOptions.value = it }
        }
        viewModelScope.launch {
            socialOptionUseCase.getAll().collect { _socialOptions.value = it }
        }
        viewModelScope.launch {
            healthOptionUseCase.getAll().collect { _healthOptions.value = it }
        }
        viewModelScope.launch {
            appetiteOptionUseCase.getAll().collect { _appetiteOptions.value = it }
        }
        viewModelScope.launch {
            dosageFormOptionUseCase.getAll().collect { _dosageFormOptions.value = it }
        }
        viewModelScope.launch {
            foodOptionUseCase.getAll().collect { _foodOptions.value = it }
        }
        viewModelScope.launch {
            mealOptionUseCase.getAll().collect { _mealOptions.value = it }
        }
        viewModelScope.launch {
            portionOptionUseCase.getAll().collect { _portionOptions.value = it }
        }
        viewModelScope.launch {
            sensationOptionUseCase.getAll().collect { _sensationOptions.value = it }
        }
        viewModelScope.launch {
            sleepActivityOptionUseCase.getAll().collect { _sleepActivityOptions.value = it }
        }
        viewModelScope.launch {
            sleepQualityOptionUseCase.getAll().collect { _sleepQualityOptions.value = it }
        }
    }

    fun toggleEmotionOption(option: EmotionOption, active: Boolean) {
        _emotionOptions.value = _emotionOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleClimateOption(option: ClimateOption, active: Boolean) {
        _climateOptions.value = _climateOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleLocationOption(option: LocationOption, active: Boolean) {
        _locationOptions.value = _locationOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleSocialOption(option: SocialOption, active: Boolean) {
        _socialOptions.value = _socialOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleHealthOption(option: HealthOption, active: Boolean) {
        _healthOptions.value = _healthOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleAppetiteOption(option: AppetiteOption, active: Boolean) {
        _appetiteOptions.value = _appetiteOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleDosageFormOption(option: DosageFormOption, active: Boolean) {
        _dosageFormOptions.value = _dosageFormOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleFoodOption(option: FoodOption, active: Boolean) {
        _foodOptions.value = _foodOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleMealOption(option: MealOption, active: Boolean) {
        _mealOptions.value = _mealOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun togglePortionOption(option: PortionOption, active: Boolean) {
        _portionOptions.value = _portionOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleSensationOption(option: SensationOption, active: Boolean) {
        _sensationOptions.value = _sensationOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleSleepActivityOption(option: SleepActivityOption, active: Boolean) {
        _sleepActivityOptions.value = _sleepActivityOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleSleepQualityOption(option: SleepQualityOption, active: Boolean) {
        _sleepQualityOptions.value = _sleepQualityOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun optionsFlow(type: JournalOptionType): StateFlow<List<JournalOption>> = when (type) {
        JournalOptionType.EMOTION -> emotionOptions
        JournalOptionType.CLIMATE -> climateOptions
        JournalOptionType.LOCATION -> locationOptions
        JournalOptionType.SOCIAL -> socialOptions
        JournalOptionType.HEALTH -> healthOptions
        JournalOptionType.APPETITE -> appetiteOptions
        JournalOptionType.DOSAGE_FORM -> dosageFormOptions
        JournalOptionType.FOOD -> foodOptions
        JournalOptionType.MEAL -> mealOptions
        JournalOptionType.PORTION -> portionOptions
        JournalOptionType.SENSATION -> sensationOptions
        JournalOptionType.SLEEP_ACTIVITY -> sleepActivityOptions
        JournalOptionType.SLEEP_QUALITY -> sleepQualityOptions
    }

    fun toggleOption(type: JournalOptionType, option: JournalOption, active: Boolean) {
        when (type) {
            JournalOptionType.EMOTION -> toggleEmotionOption(option as EmotionOption, active)
            JournalOptionType.CLIMATE -> toggleClimateOption(option as ClimateOption, active)
            JournalOptionType.LOCATION -> toggleLocationOption(option as LocationOption, active)
            JournalOptionType.SOCIAL -> toggleSocialOption(option as SocialOption, active)
            JournalOptionType.HEALTH -> toggleHealthOption(option as HealthOption, active)
            JournalOptionType.APPETITE -> toggleAppetiteOption(option as AppetiteOption, active)
            JournalOptionType.DOSAGE_FORM -> toggleDosageFormOption(option as DosageFormOption, active)
            JournalOptionType.FOOD -> toggleFoodOption(option as FoodOption, active)
            JournalOptionType.MEAL -> toggleMealOption(option as MealOption, active)
            JournalOptionType.PORTION -> togglePortionOption(option as PortionOption, active)
            JournalOptionType.SENSATION -> toggleSensationOption(option as SensationOption, active)
            JournalOptionType.SLEEP_ACTIVITY -> toggleSleepActivityOption(option as SleepActivityOption, active)
            JournalOptionType.SLEEP_QUALITY -> toggleSleepQualityOption(option as SleepQualityOption, active)
        }
    }

    fun saveOption(type: JournalOptionType, description: String) {
        viewModelScope.launch {
            when (type) {
                JournalOptionType.EMOTION -> emotionOptionUseCase.save(EmotionOption(description = description))
                JournalOptionType.CLIMATE -> climateOptionUseCase.save(ClimateOption(description = description))
                JournalOptionType.LOCATION -> locationOptionUseCase.save(LocationOption(description = description))
                JournalOptionType.SOCIAL -> socialOptionUseCase.save(SocialOption(description = description))
                JournalOptionType.HEALTH -> healthOptionUseCase.save(HealthOption(description = description))
                JournalOptionType.APPETITE -> appetiteOptionUseCase.save(AppetiteOption(description = description))
                JournalOptionType.DOSAGE_FORM -> dosageFormOptionUseCase.save(DosageFormOption(description = description))
                JournalOptionType.FOOD -> foodOptionUseCase.save(FoodOption(description = description))
                JournalOptionType.MEAL -> mealOptionUseCase.save(MealOption(description = description))
                JournalOptionType.PORTION -> portionOptionUseCase.save(PortionOption(description = description))
                JournalOptionType.SENSATION -> sensationOptionUseCase.save(SensationOption(description = description))
                JournalOptionType.SLEEP_ACTIVITY -> sleepActivityOptionUseCase.save(SleepActivityOption(description = description))
                JournalOptionType.SLEEP_QUALITY -> sleepQualityOptionUseCase.save(SleepQualityOption(description = description))
            }
        }
    }

}
