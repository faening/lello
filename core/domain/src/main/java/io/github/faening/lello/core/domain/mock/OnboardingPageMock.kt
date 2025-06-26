package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.onboarding.OnboardingPage
import io.github.faening.lello.core.designsystem.R as designsystemR

object OnboardingPageMock {
    val list = listOf(
        OnboardingPage(
            title = "Bem-vindo(a) ao Lello",
            description = "Seu diário gamificado de apoio emocional.",
            imageRes = designsystemR.drawable.img_onboarding_screen_one
        ),
        OnboardingPage(
            title = "Registre seus momentos",
            description = "Anote humor, sono, alimentação e medicação quando quiser.",
            imageRes = designsystemR.drawable.img_onboarding_screen_two
        ),
        OnboardingPage(
            title = "Jogue com o Lelo",
            description = "Preencha diários, ganhe moedas e cuide da capivara.",
            imageRes = designsystemR.drawable.img_onboarding_screen_four
        ),
        OnboardingPage(
            title = "Seu espaço, suas regras",
            description = "Gerencie perfil, notificações e privacidade no menu Perfil.",
            imageRes = designsystemR.drawable.img_onboarding_screen_four
        ),
        OnboardingPage(
            title = "Pronto para começar?",
            description = "Clique em 'Continuar' e experimente hoje mesmo.",
            imageRes = designsystemR.drawable.img_onboarding_screen_four
        )
    )
}