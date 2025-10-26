package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.AnvisaMedication

object AnvisaMedicationTestData {
    val list = listOf(
        AnvisaMedication(
            id = 1L,
            productName = "DIPIRONA",
            registrationNumber = "140750001",
            therapeuticClass = "ANALGESICOS",
            company = "86581949000148 - J. COUTINHO INDUSTRIA FARMACEUTICA LTDA",
            activeIngredient = null
        ),
        AnvisaMedication(
            id = 2L,
            productName = "ABIDOR",
            registrationNumber = "144930004",
            therapeuticClass = "ANALGESICOS",
            company = "01858973000129 - AIRELA INDÚSTRIA FARMACÊUTICA LTDA.",
            activeIngredient = null
        ),
        AnvisaMedication(
            id = 3L,
            productName = "ABRETIA",
            registrationNumber = "103900192",
            therapeuticClass = "ANTIDEPRESSIVOS",
            company = "33349473000158 - FARMOQUÍMICA S/A",
            activeIngredient = "CLORIDRATO DE DULOXETINA"
        ),
        AnvisaMedication(
            id = 4L,
            productName = "ACETICIL",
            registrationNumber = "107150069",
            therapeuticClass = "ANALGESICOS",
            company = "44010437000181 - CAZI QUIMICA FARMACEUTICA INDUSTRIA E COMERCIO LTDA",
            activeIngredient = "ÁCIDO ACETILSALICÍLICO"
        ),
        AnvisaMedication(
            id = 5L,
            productName = "ACETILESSIN",
            registrationNumber = "110570031",
            therapeuticClass = "ANALGESICOS",
            company = "28634665000176 - HEARST LABORATÓRIOS DO BRASIL LTDA",
            activeIngredient = null
        )
    )
}