package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.SleepActivityOption

object SleepActivityOptionMock {
    val list = listOf(
        SleepActivityOption(id = 1, description = "Alongamento", blocked = true, active = true),
        SleepActivityOption(id = 2, description = "Tomar café", blocked = true, active = true),
        SleepActivityOption(id = 3, description = "Tomar chá", blocked = true, active = true),
        SleepActivityOption(id = 4, description = "Celular", blocked = true, active = true),
        SleepActivityOption(id = 5, description = "Computador", blocked = true, active = true),
        SleepActivityOption(id = 6, description = "Conversa", blocked = true, active = true),
        SleepActivityOption(id = 7, description = "Leitura", blocked = true, active = true)
    )
}