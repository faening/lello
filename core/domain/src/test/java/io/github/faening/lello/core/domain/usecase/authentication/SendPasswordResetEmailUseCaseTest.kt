package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SendPasswordResetEmailUseCaseTest {
    private val repository: AuthenticationRepository = mockk()
    private val useCase = ForgotPasswordUseCase(repository)

    @Test
    fun `blank email returns error`() = runTest {
        val result = useCase("")
        assertTrue(result is AuthResult.Error)
        assertEquals("Email não pode estar vazio", (result as AuthResult.Error).message)
        coVerify(exactly = 0) { repository.sendPasswordResetEmail(any()) }
    }

    @Test
    fun `valid email calls repository`() = runTest {
        coEvery { repository.sendPasswordResetEmail("test@example.com") } returns AuthResult.Success(Unit)

        val result = useCase("test@example.com")

        assertTrue(result is AuthResult.Success)
        coVerify(exactly = 1) { repository.sendPasswordResetEmail("test@example.com") }
    }
}
