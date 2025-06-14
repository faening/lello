package io.github.faening.lello.core.domain.usecase.authentication

import io.github.faening.lello.core.domain.repository.AuthenticationRepository
import io.github.faening.lello.core.model.authentication.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCurrentUserUseCaseTest {
    private val repository: AuthenticationRepository = mockk()
    private val useCase = GetCurrentUserUseCase(repository)

    @Test
    fun `invoke should return flow from repository`() = runTest {
        val user = User(id = "1", email = "test@example.com", displayName = null, photoUrl = null)
        val flow = flowOf(user)
        every { repository.getCurrentUser() } returns flow

        val result = useCase().first()

        assertEquals(user, result)
        verify(exactly = 1) { repository.getCurrentUser() }
    }
}
