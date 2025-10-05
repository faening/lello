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

class SignUpUseCaseTest {
    private val repository: AuthenticationRepository = mockk()
    private val useCase = SignUpWithEmailAndPasswordUseCase(repository)

    @Test
    fun `blank credentials returns error`() = runTest {
        val result = useCase("", "")
        assertTrue(result is AuthResult.Error)
        assertEquals("Email e senha não podem estar vazios", (result as AuthResult.Error).message)
        coVerify(exactly = 0) { repository.signUpWithEmailAndPassword(any(), any()) }
    }

    @Test
    fun `short password returns error`() = runTest {
        val result = useCase("test@example.com", "12345")
        assertTrue(result is AuthResult.Error)
        assertEquals("A senha deve ter pelo menos 6 caracteres", (result as AuthResult.Error).message)
        coVerify(exactly = 0) { repository.signUpWithEmailAndPassword(any(), any()) }
    }

    @Test
    fun `valid credentials returns success`() = runTest {
        coEvery { repository.signUpWithEmailAndPassword("test@example.com", "123456") } returns AuthResult.Success(Unit)

        val result = useCase("test@example.com", "123456")

        assertTrue(result is AuthResult.Success)
        coVerify(exactly = 1) { repository.signUpWithEmailAndPassword("test@example.com", "123456") }
    }
}
