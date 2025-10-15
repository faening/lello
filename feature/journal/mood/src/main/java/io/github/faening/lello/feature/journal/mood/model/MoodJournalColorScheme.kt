package io.github.faening.lello.feature.journal.mood.model

import androidx.annotation.DrawableRes
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.journal.MoodType

enum class MoodJournalColorScheme(
    val label: String,
    val colorScheme: MoodColor,
    @DrawableRes val iconRes: Int
) {
    SERENE("Excelente", MoodColor.AQUAMARINE, LelloIcons.Graphic.CapybaraSerene.resId),
    JOYFUL("Bem", MoodColor.DEFAULT, LelloIcons.Graphic.CapybaraJoyful.resId),
    BALANCED("Normal", MoodColor.BLUE, LelloIcons.Graphic.CapybaraBalanced.resId),
    TROUBLED("Mal", MoodColor.ORANGE, LelloIcons.Graphic.CapybaraTroubled.resId),
    OVERWHELMED("Horrível", MoodColor.RED, LelloIcons.Graphic.CapybaraOverwhelmed.resId);
}

object MoodColorMapping {
    val moodMap = mapOf(
        MoodColor.AQUAMARINE to MoodInfo("Excelente", LelloIcons.Graphic.CapybaraSerene.resId, MoodType.SERENE),
        MoodColor.DEFAULT to MoodInfo("Bem", LelloIcons.Graphic.CapybaraJoyful.resId, MoodType.JOYFUL),
        MoodColor.BLUE to MoodInfo("Normal", LelloIcons.Graphic.CapybaraBalanced.resId, MoodType.BALANCED),
        MoodColor.ORANGE to MoodInfo("Mal", LelloIcons.Graphic.CapybaraTroubled.resId, MoodType.TROUBLED),
        MoodColor.RED to MoodInfo("Horrível", LelloIcons.Graphic.CapybaraOverwhelmed.resId, MoodType.OVERWHELMED)
    )

    val orderedMoods = listOf(
        MoodColor.AQUAMARINE,
        MoodColor.DEFAULT,
        MoodColor.BLUE,
        MoodColor.ORANGE,
        MoodColor.RED
    )

    data class MoodInfo(
        val label: String,
        @DrawableRes val iconRes: Int,
        val moodType: MoodType
    )
}