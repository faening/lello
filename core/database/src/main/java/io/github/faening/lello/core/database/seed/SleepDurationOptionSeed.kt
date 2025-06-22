package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.SleepDurationOptionEntity

object SleepDurationOptionSeed : Seed<SleepDurationOptionEntity> {
    override val data = listOf(
        SleepDurationOptionEntity(sleepDurationOptionId = 1, description = "Menos de 4h", blocked = true, active = true),
        SleepDurationOptionEntity(sleepDurationOptionId = 2, description = "4h a 6h", blocked = true, active = true),
        SleepDurationOptionEntity(sleepDurationOptionId = 3, description = "6h a 8h", blocked = true, active = true),
        SleepDurationOptionEntity(sleepDurationOptionId = 4, description = "8h a 10h", blocked = true, active = true),
        SleepDurationOptionEntity(sleepDurationOptionId = 5, description = "Mais de 10h", blocked = true, active = true),
    )
}
