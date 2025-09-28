package io.github.faening.lello.core.domain.usecase.onboarding

import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class OnboardingPreferencesUseCaseTest {
    private val repository: OnboardingRepository = mockk()
    private val useCase = OnboardingPreferencesUseCase(repository)

    @Test
    fun `hasSeenOnboarding should expose repository flow`() = runTest {
        every { repository.hasSeenOnboarding } returns flowOf(true)

        val result = useCase.hasSeenOnboarding.first()

        assertEquals(true, result)
    }

    @Test
    fun `setHasSeenOnboarding should delegate to repository`() = runTest {
        useCase.setHasSeenOnboarding()

        coVerify(exactly = 1) { repository.setHasSeenOnboarding() }
    }
}
