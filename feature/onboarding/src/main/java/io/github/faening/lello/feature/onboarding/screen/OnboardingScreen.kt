package io.github.faening.lello.feature.onboarding.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Grey100
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow500
import io.github.faening.lello.core.domain.mock.OnboardingPageMock
import io.github.faening.lello.core.model.onboarding.OnboardingPage
import io.github.faening.lello.feature.onboarding.OnboardingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import io.github.faening.lello.core.designsystem.R as designsystemR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    pages: List<OnboardingPage>,
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()
    val hasSeen by viewModel.hasSeenOnboarding.collectAsState(initial = false)
    var dontShowAgain by remember { mutableStateOf(hasSeen) }

    LelloTheme {
        OnboardingContainer(
            pagerState = pagerState,
            pages = pages,
            coroutineScope = coroutineScope,
            dontShowAgain = dontShowAgain,
            onDontShowAgainChange = { dontShowAgain = it },
            onFinish = {
                if (dontShowAgain) viewModel.setHasSeenOnboarding()
                onFinish()
            }
        )
    }
}

@Composable
private fun OnboardingContainer(
    pagerState: PagerState,
    pages: List<OnboardingPage>,
    coroutineScope: CoroutineScope,
    dontShowAgain: Boolean,
    onDontShowAgainChange: (Boolean) -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        bottomBar = {
            OnboardingBottomBar(
                pagerState = pagerState,
                pages = pages,
                coroutineScope = coroutineScope,
                onFinish = onFinish
            )
        }
    ) { paddingValues ->
        OnboardingContent(
            pagerState = pagerState,
            pages = pages,
            dontShowAgain = dontShowAgain,
            onDontShowAgainChange = onDontShowAgainChange,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun OnboardingBottomBar(
    pagerState: PagerState,
    pages: List<OnboardingPage>,
    coroutineScope: CoroutineScope,
    onFinish: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(Dimension.spacingRegular)
    ) {
        val next = pagerState.currentPage + 1
        if (next < pages.size) {
            LelloFloatingActionButton(
                icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
                contentDescription = "Próximo",
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(next)
                    }
                }
            )
        } else {
            LelloFilledButton(
                label = "Concluir",
                onClick = onFinish
            )
        }
    }
}

@Composable
private fun OnboardingContent(
    pagerState: PagerState,
    pages: List<OnboardingPage>,
    dontShowAgain: Boolean,
    onDontShowAgainChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageView(
                page = pages[page],
                showCheckbox = page == pages.lastIndex,
                checked = dontShowAgain,
                onCheckedChange = onDontShowAgainChange
            )
        }

        OnboardingHorizontalPagerIndicator(
            pagerState = pagerState,
            pages = pages
        )
    }
}

@Composable
private fun OnboardingPageView(
    page: OnboardingPage,
    showCheckbox: Boolean,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.spacingExtraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))
        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        if (showCheckbox) {
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange
                )
                // Spacer(modifier = Modifier.width(Dimension.ExtraSmall))
                Text(
                    text = "Não mostrar mais a tela de boas-vindas",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
private fun OnboardingHorizontalPagerIndicator(
    pagerState: PagerState,
    pages: List<OnboardingPage>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.spacingExtraLarge),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pages.size) { index ->
            val color = if (pagerState.currentPage == index) Yellow500 else Grey100
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (pagerState.currentPage == index) 12.dp else 8.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    val pages = OnboardingPageMock.list

    LelloTheme {
        OnboardingContainer(
            pagerState = rememberPagerState(pageCount = { pages.size }),
            pages = pages,
            coroutineScope = rememberCoroutineScope(),
            dontShowAgain = false,
            onDontShowAgainChange = {},
            onFinish = {}
        )
    }
}