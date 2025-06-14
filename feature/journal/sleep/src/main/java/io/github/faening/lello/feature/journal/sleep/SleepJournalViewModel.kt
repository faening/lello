package io.github.faening.lello.feature.journal.sleep

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SleepJournalViewModel @Inject constructor(

) : ViewModel() {

    private val _sleepDuration = MutableStateFlow("")
    val sleepDuration: StateFlow<String> = _sleepDuration

    fun updateSleepDuration(duration: String) {
        _sleepDuration.value = duration
    }
}