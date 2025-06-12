package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.AuthResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class SignOutUseCaseTest {
    private val repository: AuthenticationRepository = mockk()
    private val useCase = SignOutUseCase(repository)

    @Test
    fun `invoke should call repository`() = runTest {
        coEvery { repository.signOut() } returns AuthResult.Success(Unit)

        val result = useCase()

        assertTrue(result is AuthResult.Success)
        coVerify(exactly = 1) { repository.signOut() }
    }
}
