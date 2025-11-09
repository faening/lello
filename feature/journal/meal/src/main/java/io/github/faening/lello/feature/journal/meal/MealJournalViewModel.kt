package io.github.faening.lello.feature.journal.meal

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.designsystem.media.LelloMedia
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.meal.SaveMealJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.appetite.GetAllAppetiteOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.food.GetAllFoodOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetAllLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.meal.GetAllMealOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.portion.GetAllPortionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.GetAllSocialOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillis
import io.github.faening.lello.core.domain.util.toLocalDateTime
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.option.AppetiteOption
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.MealOption
import io.github.faening.lello.core.model.option.PortionOption
import io.github.faening.lello.core.model.option.SocialOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MealJournalViewModel @Inject constructor(
    private val saveMealJournalUseCase: SaveMealJournalUseCase,
    private val rewardCalculatorService: RewardCalculatorService,
    private val getAllAppetiteOptionUseCase: GetAllAppetiteOptionUseCase,
    private val getAllFoodOptionUseCase: GetAllFoodOptionUseCase,
    private val getAllLocationOptionUseCase: GetAllLocationOptionUseCase,
    private val getAllMealOptionUseCase: GetAllMealOptionUseCase,
    private val getAllPortionOptionUseCase: GetAllPortionOptionUseCase,
    private val getAllSocialOptionUseCase: GetAllSocialOptionUseCase,
) : ViewModel() {

    private val _mealTime = MutableStateFlow(LocalDateTime.now().toEpochMillis())
    val mealTime: StateFlow<String> = _mealTime
        .map { timestamp ->
            val dateTime = timestamp.toLocalDateTime()
            val hour = dateTime.hour.toString().padStart(2, '0')
            val minute = dateTime.minute.toString().padStart(2, '0')
            "$hour:$minute"
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ""
        )

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

    private val _coinsAcquired = MutableStateFlow(50)
    val coinsAcquired: StateFlow<Int> = _coinsAcquired

    private var _exoPlayer: ExoPlayer? = null
    val exoPlayer: ExoPlayer? get() = _exoPlayer

    init {
        loadMealOptions()
        loadAppetiteOptions()
        loadFoodOptions()
        loadPortionOptions()
        loadLocationOptions()
        loadSocialOptions()
    }

    private fun loadMealOptions() {
        viewModelScope.launch {
            getAllMealOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _mealOptions.value = it }
        }
    }

    private fun loadAppetiteOptions() {
        viewModelScope.launch {
            getAllAppetiteOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _appetiteOptions.value = it }
        }
    }

    private fun loadFoodOptions() {
        viewModelScope.launch {
            getAllFoodOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _foodOptions.value = it }
        }
    }

    private fun loadPortionOptions() {
        viewModelScope.launch {
            getAllPortionOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _portionOptions.value = it }
        }
    }

    private fun loadLocationOptions() {
        viewModelScope.launch {
            getAllLocationOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _locationOptions.value = it }
        }
    }

    private fun loadSocialOptions() {
        viewModelScope.launch {
            getAllSocialOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _socialOptions.value = it }
        }
    }

    fun updateMealTime(hour: Int, minute: Int) {
        val now = LocalDateTime.now()
        val dateTime = now.withHour(hour).withMinute(minute).withSecond(0).withNano(0)
        _mealTime.value = dateTime.toEpochMillis()
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
            mealTime = _mealTime.value,
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

    /**
     * Prepara o ExoPlayer com o vídeo correspondente ao humor atual.
     *
     * @param context Contexto necessário para a criação do ExoPlayer.
     */
    fun prepareVideo(context: Context) {
        val video = LelloMedia.Video.JournalSummaryBackgroundYellow
        _exoPlayer?.release()
        _exoPlayer = ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${video.resId}")
            setMediaItem(mediaItem)
            repeatMode = Player.REPEAT_MODE_ALL
            volume = 0f
            prepare()
        }
    }
}