package io.github.faening.lello.feature.diary

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.authentication.BiometricAuthenticationUseCase
import io.github.faening.lello.core.domain.usecase.authentication.ValidatePasswordUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.GetAllMealJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.GetAllMoodJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.GetAllSleepJournalsUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardAmountByOriginUseCase
import io.github.faening.lello.core.model.authentication.AuthResult
import io.github.faening.lello.core.model.authentication.AuthenticationState
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val biometricAuthUseCase: BiometricAuthenticationUseCase,
    private val getAllMealJournalsUseCase: GetAllMealJournalsUseCase,
    private val getAllMoodJournalsUseCase: GetAllMoodJournalsUseCase,
    private val getAllSleepJournalsUseCase: GetAllSleepJournalsUseCase,
    private val getRewardAmountByOriginUseCase: GetRewardAmountByOriginUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    private val _authenticationState = MutableStateFlow<AuthenticationState>(AuthenticationState.Idle)
    val authenticationState: StateFlow<AuthenticationState> = _authenticationState.asStateFlow()

    private val _canUseBiometricAuth = MutableStateFlow(false)
    val canUseBiometricAuth: StateFlow<Boolean> = _canUseBiometricAuth.asStateFlow()

    private val _mealJournals = MutableStateFlow<List<MealJournal>>(emptyList())
    val mealJournals: StateFlow<List<MealJournal>> = _mealJournals

    private val _moodJournals = MutableStateFlow<List<MoodJournal>>(emptyList())
    val moodJournals: StateFlow<List<MoodJournal>> = _moodJournals

    private val _sleepJournals = MutableStateFlow<List<SleepJournal>>(emptyList())
    val sleepJournals: StateFlow<List<SleepJournal>> = _sleepJournals

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _selectedMoodJournal = MutableStateFlow<MoodJournal?>(null)
    val selectedMoodJournal: StateFlow<MoodJournal?> = _selectedMoodJournal.asStateFlow()

    private val _selectedMealJournal = MutableStateFlow<MealJournal?>(null)
    val selectedMealJournal: StateFlow<MealJournal?> = _selectedMealJournal.asStateFlow()

    private val _selectedSleepJournal = MutableStateFlow<SleepJournal?>(null)
    val selectedSleepJournal: StateFlow<SleepJournal?> = _selectedSleepJournal.asStateFlow()

    init {
        loadMoodJournals()
        loadMealJournal()
        loadSleepJournal()
        checkBiometricAvailability()
    }

    private fun loadMoodJournals() {
        viewModelScope.launch {
            getAllMoodJournalsUseCase.invoke().collect { _moodJournals.value = it }
        }
    }

    private fun loadMealJournal() {
        viewModelScope.launch {
            getAllMealJournalsUseCase.invoke().collect { _mealJournals.value = it }
        }
    }

    private fun loadSleepJournal() {
        viewModelScope.launch {
            getAllSleepJournalsUseCase.invoke().collect { _sleepJournals.value = it }
        }
    }

    private fun checkBiometricAvailability() {
        viewModelScope.launch {
            runCatching {
                _canUseBiometricAuth.value = biometricAuthUseCase.shouldUseBiometricAuthentication()
            }.onFailure {
                _canUseBiometricAuth.value = false
            }
        }
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun setSelectedMoodJournal(journalId: Long) {
        viewModelScope.launch {
            _selectedMoodJournal.value = _moodJournals.value.find { it.id == journalId }
        }
    }

    fun setSelectedMealJournal(journalId: Long) {
        viewModelScope.launch {
            _selectedMealJournal.value = _mealJournals.value.find { it.id == journalId }
        }
    }

    fun setSelectedSleepJournal(journalId: Long) {
        viewModelScope.launch {
            _selectedSleepJournal.value = _sleepJournals.value.find { it.id == journalId }
        }
    }

    suspend fun getRewardAmount(origin: RewardOrigin, originId: Long): Int {
        return getRewardAmountByOriginUseCase.invoke(origin, originId) ?: 0
    }

    fun authenticateWithPassword(password: String) {
        viewModelScope.launch {
            _authenticationState.value = AuthenticationState.Loading

            val result = validatePasswordUseCase(password)

            _authenticationState.value = when (result) {
                is AuthResult.Success -> AuthenticationState.Success
                is AuthResult.Error -> AuthenticationState.Error(result.message)
                is AuthResult.Loading<*> -> AuthenticationState.Loading
            }
        }
    }

    suspend fun authenticateWithBiometric(activity: FragmentActivity) {
        val isBiometricAvailable = biometricAuthUseCase.shouldUseBiometricAuthentication()

        if (!isBiometricAvailable) {
            _authenticationState.value = AuthenticationState.Error("Biometria não configurada. Use a senha.")
            return
        }

        _authenticationState.value = AuthenticationState.Loading

        val result = biometricAuthUseCase.authenticate(
            activity = activity,
            title = "Autenticação Biométrica",
            subtitle = "Confirme sua identidade para acessar os detalhes",
            negativeButtonText = "Usar Senha"
        )

        _authenticationState.value = when (result) {
            is AuthResult.Success -> AuthenticationState.Success
            is AuthResult.Error -> AuthenticationState.Error(result.message)
            is AuthResult.Loading<*> -> AuthenticationState.Loading
        }
    }

    fun resetAuthenticationState() {
        _authenticationState.value = AuthenticationState.Idle
    }
}
