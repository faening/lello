package io.github.faening.lello.feature.settings.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.settings.SettingsViewModel
import io.github.faening.lello.feature.settings.model.TermsAndPrivacyContent
import io.github.faening.lello.feature.settings.model.TermsListItem

@Composable
fun SettingsTermsAndPrivacyScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    SettingsTermsAndPrivacyScreenContent(onBack = onBack)
}

@Composable
private fun SettingsTermsAndPrivacyScreenContent(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            SettingsTermsAndPrivacyTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            // Terms of Use Section
            Text(
                text = "Termos de uso",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimension.spacingLarge)
            )
            TermsAndPrivacyContent.termsOfUse.forEach { section ->
                SettingsTermsAndPrivacyListSection(
                    title = section.title,
                    items = section.items
                )
            }

            // Privacy Policy Section
            Text(
                text = "Políticas de privacidade",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimension.spacingLarge)
            )
            TermsAndPrivacyContent.privacyPolicy.forEach { section ->
                SettingsTermsAndPrivacyListSection(
                    title = section.title,
                    items = section.items
                )
            }

            // Last Updated Text
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Atualizado em: ")
                    }
                    append(TermsAndPrivacyContent.LAST_UPDATED)
                },
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun SettingsTermsAndPrivacyTopAppBar(
    onBack: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Uso e Privacidade"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
fun SettingsTermsAndPrivacyListSection(
    title: String? = null,
    items: List<TermsListItem>,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimension.spacingRegular)
            )
        }

        items.forEachIndexed { index, item ->
            Text(
                text = buildAnnotatedString {
                    if (item.title.isNotBlank()) {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(item.title)
                            if (!item.title.endsWith(":")) append(":")
                            append(" ")
                        }
                    }
                    append(item.content)
                },
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (index == items.lastIndex)
                            Dimension.spacingExtraLarge
                        else
                            Dimension.spacingMedium
                    )
            )
        }
    }
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun SettingsTermsAndPrivacyScreenPreview_LightMode() {
    LelloTheme {
        SettingsTermsAndPrivacyScreenContent(
            onBack = {}
        )
    }
}

// endregion Previews