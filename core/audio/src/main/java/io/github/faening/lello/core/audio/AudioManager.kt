package io.github.faening.lello.core.audio

import android.content.Context
import android.media.MediaPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AudioManager {
    private var mediaPlayer: MediaPlayer? = null
    private var fadeJob: Job? = null

    fun play(context: Context, track: AudioTrack) {
        stop() // Para qualquer música anterior

        mediaPlayer = MediaPlayer.create(context, track.rawResId).apply {
            isLooping = true
            setVolume(0f, 0f) // Começa silencioso
            start()
        }
        fadeJob = fadeVolume(0f, 1f, durationMs = 800)
    }

    fun stop() {
        fadeJob?.cancel()
        fadeJob = null
        mediaPlayer?.let { player ->
            fadeJob = fadeVolume(player.volumeLeft, 0f, durationMs = 600) {
                player.stop()
                player.release()
                mediaPlayer = null
            }
        } ?: run {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    // Helper: Faz o fade de volume entre dois valores
    private fun fadeVolume(
        from: Float,
        to: Float,
        durationMs: Long,
        onEnd: (() -> Unit)? = null
    ): Job = CoroutineScope(Dispatchers.Main).launch {
        val steps = 20
        val stepTime = durationMs / steps
        for (i in 0..steps) {
            val progress = i / steps.toFloat()
            val volume = from + (to - from) * progress
            mediaPlayer?.setVolume(volume, volume)
            delay(stepTime)
        }
        onEnd?.invoke()
    }

    // Helper para pegar o volume atual (não é nativo, só pra manter contexto)
    private val MediaPlayer.volumeLeft: Float
        get() = 1f // Aqui não tem getter real, mas mantém a ideia
}
