package io.github.faening.lello.core.model.option

data class HealthOption(
    override val id: Long = 0L,
    override val description: String,
    override val blocked: Boolean = false,
    override val active: Boolean = true,
    override val selected: Boolean = false
) : JournalOption
