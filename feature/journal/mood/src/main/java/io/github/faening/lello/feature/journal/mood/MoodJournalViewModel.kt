package io.github.faening.lello.feature.journal.mood

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.designsystem.media.LelloMedia
import io.github.faening.lello.core.designsystem.media.LelloVideo
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.mood.SaveMoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetAllClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.GetAllEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.health.GetAllHealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetAllLocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.GetAllSocialOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillis
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.MoodType
import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SocialOption
import io.github.faening.lello.feature.journal.mood.model.MoodColorMapping
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MoodJournalViewModel @Inject constructor(
    private val saveMoodJournalUseCase: SaveMoodJournalUseCase,
    private val rewardCalculatorService: RewardCalculatorService,
    private val getAllClimateOptionUseCase: GetAllClimateOptionUseCase,
    private val getAllEmotionOptionUseCase: GetAllEmotionOptionUseCase,
    private val getAllHealthOptionUseCase: GetAllHealthOptionUseCase,
    private val getAllLocationOptionUseCase: GetAllLocationOptionUseCase,
    private val getAllSocialOptionUseCase: GetAllSocialOptionUseCase,
) : ViewModel() {

    private val _currentMood = MutableStateFlow(MoodColor.DEFAULT)
    val currentMood: StateFlow<MoodColor> = _currentMood

    private val _entryDateTime = MutableStateFlow<LocalDateTime?>(null)
    val entryDateTime: StateFlow<String> = _entryDateTime
        .map { dt -> dt?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "" }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    private val _emotionOptions = MutableStateFlow<List<EmotionOption>>(emptyList())
    val emotionOptions: StateFlow<List<EmotionOption>> = _emotionOptions

    private val _healthOptions = MutableStateFlow<List<HealthOption>>(emptyList())
    val healthOptions: StateFlow<List<HealthOption>> = _healthOptions

    private val _climateOptions = MutableStateFlow<List<ClimateOption>>(emptyList())
    val climateOptions: StateFlow<List<ClimateOption>> = _climateOptions

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _socialOptions = MutableStateFlow<List<SocialOption>>(emptyList())
    val socialOptions: StateFlow<List<SocialOption>> = _socialOptions

    private val _reflection = MutableStateFlow("")
    val reflection: StateFlow<String> = _reflection

    private val _moodJournal = MutableStateFlow<MoodJournal?>(null)

    private val _coinsAcquired = MutableStateFlow(50)
    val coinsAcquired: StateFlow<Int> = _coinsAcquired

    private var _exoPlayer: ExoPlayer? = null
    val exoPlayer: ExoPlayer? get() = _exoPlayer

    init {
        loadEmotionOptions()
        loadHealthOptions()
        loadClimateOptions()
        loadLocationOptions()
        loadSocialOptions()
    }

    private fun loadEmotionOptions() {
        viewModelScope.launch {
            getAllEmotionOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _emotionOptions.value = it }
        }
    }

    private fun loadHealthOptions() {
        viewModelScope.launch {
            getAllHealthOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _healthOptions.value = it }
        }
    }

    private fun loadClimateOptions() {
        viewModelScope.launch {
            getAllClimateOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _climateOptions.value = it }
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

    /**
     * Atualiza o humor selecionado pelo usuário.
     */
    fun updateMood(moodColor: MoodColor) {
        _currentMood.value = moodColor
    }

    /**
     * Captura o horário de início do diário apenas se ainda não tiver sido definido. Essa função deve ser chamada ao
     * abrir o fluxo de diário de humor.
     */
    fun captureEntryDateTime() {
        if (_entryDateTime.value == null) {
            _entryDateTime.value = LocalDateTime.now()
        }
    }

    fun toggleEmotionSelection(description: String) {
        _emotionOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleClimateSelection(description: String) {
        _climateOptions.update { list ->
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

    fun toggleHealthSelection(description: String) {
        _healthOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun updateReflection(text: String) {
        _reflection.value = text
        updateCoinsAcquired()
    }

    /**
     * Atualiza a quantidade de moedas adquiridas com base no diário de humor atual.
     *
     * Essa função deve ser chamada sempre que houver uma alteração relevante no diário que possa impactar a
     * recompensa, como a seleção de opções ou a adição de uma reflexão.
     */
    private fun updateCoinsAcquired() {
        val moodJournal = buildMoodJournal()
        val points = rewardCalculatorService.calculateForMoodJournal(moodJournal)
        _coinsAcquired.value = points
    }

    fun saveMoodJournal() {
        viewModelScope.launch {
            val journal = buildMoodJournal()
            saveMoodJournalUseCase.invoke(journal)
            _moodJournal.value = journal
        }
    }

    private fun buildMoodJournal(): MoodJournal {
        val millis = (_entryDateTime.value ?: LocalDateTime.now()).toEpochMillis()
        return MoodJournal(
            mood = getMoodTypeFromMoodColor(currentMood.value),
            reflection = reflection.value,
            emotionOptions = emotionOptions.value.filter { it.selected },
            climateOptions = climateOptions.value.filter { it.selected },
            locationOptions = locationOptions.value.filter { it.selected },
            socialOptions = socialOptions.value.filter { it.selected },
            healthOptions = healthOptions.value.filter { it.selected },
            createdAt = millis,
        )
    }

    private fun getMoodTypeFromMoodColor(moodColor: MoodColor): MoodType {
        return MoodColorMapping.moodMap[moodColor]?.moodType ?: MoodType.JOYFUL
    }

    /**
     * Prepara o ExoPlayer com o vídeo correspondente ao humor atual.
     *
     * @param context Contexto necessário para a criação do ExoPlayer.
     */
    fun prepareVideo(context: Context) {
        viewModelScope.launch {
            currentMood.collect { moodColor ->
                val video = getVideoByMoodColor(moodColor)
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
    }

    /**
     * Retorna o vídeo correspondente à cor do humor fornecida.
     *
     * @param moodColor A cor do humor selecionada.
     * @return O vídeo associado à cor do humor.
     */
    private fun getVideoByMoodColor(moodColor: MoodColor): LelloVideo {
        return when (moodColor) {
            MoodColor.DEFAULT -> LelloMedia.Video.JournalSummaryBackgroundYellow
            MoodColor.INVERSE -> LelloMedia.Video.JournalSummaryBackgroundYellow
            MoodColor.AQUAMARINE -> LelloMedia.Video.JournalSummaryBackgroundAquamarine
            MoodColor.BLUE -> LelloMedia.Video.JournalSummaryBackgroundBlue
            MoodColor.ORANGE -> LelloMedia.Video.JournalSummaryBackgroundOrange
            MoodColor.RED -> LelloMedia.Video.JournalSummaryBackgroundRed
            MoodColor.SECONDARY -> LelloMedia.Video.JournalSummaryBackgroundYellow
        }
    }

    override fun onCleared() {
        _exoPlayer?.release()
        _exoPlayer = null
        super.onCleared()
    }
}