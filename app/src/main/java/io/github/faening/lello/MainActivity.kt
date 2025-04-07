package io.github.faening.lello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.ui.LelloApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LelloTheme(dynamicColor = false) {
                LelloApp()
            }
        }
    }
}