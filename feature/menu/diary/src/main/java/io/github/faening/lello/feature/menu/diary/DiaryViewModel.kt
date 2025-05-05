package io.github.faening.lello.feature.menu.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.JournalCategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    journalCategoryUseCase: JournalCategoryUseCase,
) : ViewModel() {

    // Estado UI para a tela
    private val _uiState = MutableStateFlow(DiaryUiState())
    val uiState: StateFlow<DiaryUiState> = _uiState

    // Lista de diários
    val diaries = journalCategoryUseCase.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadDiaries()
    }

    private fun loadDiaries() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // O diaryRepository já está sendo observado como StateFlow
                _uiState.update { it.copy(isLoading = false, error = null) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load diaries"
                    )
                }
            }
        }
    }

    fun toggleDiaryStatus(diaryId: Int, active: Boolean) {
//        viewModelScope.launch {
//            try {
//                val success = diaryRepository.toggleActiveStatus(diaryId, active)
//                if (!success) {
//                    _uiState.update {
//                        it.copy(error = "This diary cannot be modified")
//                    }
//                }
//            } catch (e: Exception) {
//                _uiState.update {
//                    it.copy(error = e.message ?: "Failed to update diary status")
//                }
//            }
//        }
    }

    fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }
}

// Estado da UI
data class DiaryUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)
