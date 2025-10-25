package io.github.faening.lello.feature.journal.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.emotion.GetAllEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.SaveEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.UpdateEmotionOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.dosageform.GetAllDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.dosageform.SaveDosageFormOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.dosageform.UpdateDosageFormOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.HealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.MealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.PortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAllAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.SaveAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.UpdateAppetiteOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetAllClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.SaveClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.UpdateClimateOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetAllFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.SaveFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.UpdateFoodOptionActiveStatusUseCase
import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.DosageFormOption
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.JournalOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.MealOption
import io.github.faening.lello.core.model.option.PortionOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepQualityOption
import io.github.faening.lello.core.model.option.SleepSensationOption
import io.github.faening.lello.core.model.option.SocialOption
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsJournalViewModel @Inject constructor(
    // Emotion Options
    private val getAllEmotionOptionUseCase: GetAllEmotionOptionUseCase,
    private val saveEmotionOptionUseCase: SaveEmotionOptionUseCase,
    private val updateEmotionOptionActiveStatusUseCase: UpdateEmotionOptionActiveStatusUseCase,

    // Appetite Options
    private val getAllAppetiteOptionUseCase: GetAllAppetiteOptionUseCase,
    private val saveAppetiteOptionUseCase: SaveAppetiteOptionUseCase,
    private val updateAppetiteOptionActiveStatusUseCase: UpdateAppetiteOptionActiveStatusUseCase,

    // Climate Options
    private val getAllClimateOptionUseCase: GetAllClimateOptionUseCase,
    private val saveClimateOptionUseCase: SaveClimateOptionUseCase,
    private val updateClimateOptionActiveStatusUseCase: UpdateClimateOptionActiveStatusUseCase,

    // Food Options
    private val getAllFoodOptionUseCase: GetAllFoodOptionUseCase,
    private val saveFoodOptionUseCase: SaveFoodOptionUseCase,
    private val updateFoodOptionActiveStatusUseCase: UpdateFoodOptionActiveStatusUseCase,

    private val locationOptionUseCase: LocationOptionUseCase,
    private val socialOptionUseCase: SocialOptionUseCase,
    private val healthOptionUseCase: HealthOptionUseCase,

    // DosageForm Options
    private val getAllDosageFormOptionUseCase: GetAllDosageFormOptionUseCase,
    private val saveDosageFormOptionUseCase: SaveDosageFormOptionUseCase,
    private val updateDosageFormOptionActiveStatusUseCase: UpdateDosageFormOptionActiveStatusUseCase,

    private val mealOptionUseCase: MealOptionUseCase,
    private val portionOptionUseCase: PortionOptionUseCase,
    private val sensationOptionUseCase: SleepSensationOptionUseCase,
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

    private val _sensationOptions = MutableStateFlow<List<SleepSensationOption>>(emptyList())
    val sensationOptions: StateFlow<List<SleepSensationOption>> = _sensationOptions

    private val _sleepActivityOptions = MutableStateFlow<List<SleepActivityOption>>(emptyList())
    val sleepActivityOptions: StateFlow<List<SleepActivityOption>> = _sleepActivityOptions

    private val _sleepQualityOptions = MutableStateFlow<List<SleepQualityOption>>(emptyList())
    val sleepQualityOptions: StateFlow<List<SleepQualityOption>> = _sleepQualityOptions

    init {
        viewModelScope.launch {
            getAllEmotionOptionUseCase.invoke().collect { _emotionOptions.value = it }
        }
        viewModelScope.launch {
            getAllClimateOptionUseCase.invoke().collect { _climateOptions.value = it }
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
            getAllAppetiteOptionUseCase.invoke().collect { _appetiteOptions.value = it }
        }
        viewModelScope.launch {
            getAllDosageFormOptionUseCase.invoke().collect { _dosageFormOptions.value = it }
        }
        viewModelScope.launch {
            getAllFoodOptionUseCase.invoke().collect { _foodOptions.value = it }
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

    fun toggleSensationOption(option: SleepSensationOption, active: Boolean) {
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
        JournalOptionType.SLEEP_SENSATION -> sensationOptions
        JournalOptionType.SLEEP_ACTIVITY -> sleepActivityOptions
        JournalOptionType.SLEEP_QUALITY -> sleepQualityOptions
    }

    fun toggleOption(type: JournalOptionType, option: JournalOption, active: Boolean) {
        viewModelScope.launch {
            when (type) {
                JournalOptionType.EMOTION -> {
                    updateEmotionOptionActiveStatusUseCase.invoke((option as EmotionOption).copy(active = active))
                    toggleEmotionOption(option, active)
                }
                JournalOptionType.CLIMATE -> {
                    updateClimateOptionActiveStatusUseCase.invoke((option as ClimateOption).copy(active = active))
                    toggleClimateOption(option, active)
                }
                JournalOptionType.LOCATION -> {
                    locationOptionUseCase.updateActiveStatus((option as LocationOption).copy(active = active))
                    toggleLocationOption(option, active)
                }
                JournalOptionType.SOCIAL -> {
                    socialOptionUseCase.updateActiveStatus((option as SocialOption).copy(active = active))
                    toggleSocialOption(option, active)
                }
                JournalOptionType.HEALTH -> {
                    healthOptionUseCase.updateActiveStatus((option as HealthOption).copy(active = active))
                    toggleHealthOption(option, active)
                }
                JournalOptionType.APPETITE -> {
                    updateAppetiteOptionActiveStatusUseCase.invoke((option as AppetiteOption).copy(active = active))
                    toggleAppetiteOption(option, active)
                }
                JournalOptionType.DOSAGE_FORM -> {
                    updateDosageFormOptionActiveStatusUseCase.invoke((option as DosageFormOption).copy(active = active))
                    toggleDosageFormOption(option, active)
                }
                JournalOptionType.FOOD -> {
                    updateFoodOptionActiveStatusUseCase.invoke((option as FoodOption).copy(active = active))
                    toggleFoodOption(option, active)
                }
                JournalOptionType.MEAL -> {
                    mealOptionUseCase.updateActiveStatus((option as MealOption).copy(active = active))
                    toggleMealOption(option, active)
                }
                JournalOptionType.PORTION -> {
                    portionOptionUseCase.updateActiveStatus((option as PortionOption).copy(active = active))
                    togglePortionOption(option, active)
                }
                JournalOptionType.SLEEP_SENSATION -> {
                    sensationOptionUseCase.updateActiveStatus((option as SleepSensationOption).copy(active = active))
                    toggleSensationOption(option, active)
                }
                JournalOptionType.SLEEP_ACTIVITY -> {
                    sleepActivityOptionUseCase.updateActiveStatus((option as SleepActivityOption).copy(active = active))
                    toggleSleepActivityOption(option, active)
                }
                JournalOptionType.SLEEP_QUALITY -> {
                    sleepQualityOptionUseCase.updateActiveStatus((option as SleepQualityOption).copy(active = active))
                    toggleSleepQualityOption(option, active)
                }
            }
        }
    }

    fun saveOption(type: JournalOptionType, description: String) {
        viewModelScope.launch {
            when (type) {
                JournalOptionType.EMOTION -> saveEmotionOptionUseCase.invoke(EmotionOption(description = description))
                JournalOptionType.CLIMATE -> saveClimateOptionUseCase.invoke(ClimateOption(description = description))
                JournalOptionType.LOCATION -> locationOptionUseCase.save(LocationOption(description = description))
                JournalOptionType.SOCIAL -> socialOptionUseCase.save(SocialOption(description = description))
                JournalOptionType.HEALTH -> healthOptionUseCase.save(HealthOption(description = description))
                JournalOptionType.APPETITE -> saveAppetiteOptionUseCase.invoke(AppetiteOption(description = description))
                JournalOptionType.DOSAGE_FORM -> saveDosageFormOptionUseCase.invoke(DosageFormOption(description = description))
                JournalOptionType.FOOD -> saveFoodOptionUseCase.invoke(FoodOption(description = description))
                JournalOptionType.DOSAGE_FORM -> saveDosageFormOptionUseCase.invoke(DosageFormOption(description = description))
                JournalOptionType.FOOD -> saveFoodOptionUseCase.invoke(FoodOption(description = description))
                JournalOptionType.MEAL -> mealOptionUseCase.save(MealOption(description = description))
                JournalOptionType.PORTION -> portionOptionUseCase.save(PortionOption(description = description))
                JournalOptionType.SLEEP_SENSATION -> sensationOptionUseCase.save(SleepSensationOption(description = description))
                JournalOptionType.SLEEP_ACTIVITY -> sleepActivityOptionUseCase.save(SleepActivityOption(description = description))
                JournalOptionType.SLEEP_QUALITY -> sleepQualityOptionUseCase.save(SleepQualityOption(description = description))
            }
        }
    }
}