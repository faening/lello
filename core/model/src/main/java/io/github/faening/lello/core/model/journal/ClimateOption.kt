package io.github.faening.lello.core.model.journal

data class ClimateOption(
    override val id: Int?,
    override val description: String,
    override val blocked: Boolean = false,
    override val active: Boolean = true
) : JournalOption
