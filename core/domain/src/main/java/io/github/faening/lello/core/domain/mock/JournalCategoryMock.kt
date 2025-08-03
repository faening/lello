package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.JournalCategory

object JournalCategoryMock {
    val list = listOf(
        JournalCategory(
            id = 1,
            name = "Humor",
            shortDescription = "Registre suas emoções e compreenda melhor seus estados emocionais",
            longDescription = "Registre suas emoções e compreenda melhor seus estados emocionais..."
        ),
        JournalCategory(
            id = 2,
            name = "Alimentação",
            shortDescription = "Registre sua alimentação e compreenda como ela afeta sua saúde emocional",
            longDescription = "Registre sua alimentação e compreenda como ela afeta sua saúde emocional..."
        ),
        JournalCategory(
            id = 3,
            name = "Sono",
            shortDescription = "Monitore seu sono e descubra como ele impacta seu bem-estar emocional",
            longDescription = "Monitore seu sono e descubra como ele impacta seu bem-estar emocional..."
        ),
        JournalCategory(
            id = 4,
            name = "Medicamentos",
            shortDescription = "Acompanhe sua medicação diária e monitore sua adaptação ao tratamento",
            longDescription = "Acompanhe sua medicação diária e monitore sua adaptação ao tratamento..."
        )
    )
}