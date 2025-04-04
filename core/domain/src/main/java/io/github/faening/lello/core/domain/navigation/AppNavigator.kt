package io.github.faening.lello.core.domain.navigation

/**
 * Interface que define todas as possíveis navegações entre os módulos da aplicação.
 * Centraliza os callbacks de navegação em um único objeto.
 */
interface AppNavigator {
    fun navigateToHome()
    fun navigateToMealDiary()
    fun navigateToMedicationDiary()
    fun navigateToMoodDiary()
    fun navigateToSleepDiary()

    // Operações comuns de navegação
    fun navigateUp()
    fun popBackStack(): Boolean
}