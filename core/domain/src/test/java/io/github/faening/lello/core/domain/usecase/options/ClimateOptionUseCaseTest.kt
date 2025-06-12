package io.github.faening.lello.core.domain.usecase.options

import io.github.faening.lello.core.domain.mock.ClimateOptionMock
import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.model.journal.ClimateOption
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ClimateOptionUseCaseTest {
    private val repository: OptionResources<ClimateOption> = mockk()
    private val useCase = ClimateOptionUseCase(repository)

    @Test
    fun `getAll should delegate to repository`() = runTest {
        val expected = ClimateOptionMock.list
        every { repository.getAll(false, true, false, true) } returns flowOf(expected)

        val result = useCase.getAll().first()

        assertEquals(expected, result)
        coVerify(exactly = 0) { repository.insert(any()) }
        verify(exactly = 1) { repository.getAll(false, true, false, true) }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `getById with invalid id throws`() {
        useCase.getById(0L)
    }

    @Test
    fun `save formats description and inserts`() = runTest {
        val option = ClimateOption(id = 0, description = "nublado")
        coEvery { repository.insert(any()) } returns 1L

        useCase.save(option)

        coVerify(exactly = 1) { repository.insert(option.copy(description = "Nublado")) }
    }
}
