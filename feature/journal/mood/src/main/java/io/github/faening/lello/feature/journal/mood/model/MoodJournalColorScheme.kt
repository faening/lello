package io.github.faening.lello.feature.journal.mood.model

import androidx.annotation.DrawableRes
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.journal.MoodType
import io.github.faening.lello.core.designsystem.R as designSystemR

enum class MoodJournalColorScheme(
    val label: String,
    val colorScheme: MoodColor,
    @DrawableRes val iconRes: Int
) {
    SERENE      ("Excelente", MoodColor.AQUAMARINE, designSystemR.drawable.ic_capy_serene),
    JOYFUL      ("Bem",       MoodColor.DEFAULT,    designSystemR.drawable.ic_capy_joyful),
    BALANCED    ("Normal",    MoodColor.BLUE,       designSystemR.drawable.ic_capy_balanced),
    TROUBLED    ("Mal",       MoodColor.ORANGE,     designSystemR.drawable.ic_capy_troubled),
    OVERWHELMED ("Horrível",  MoodColor.RED,        designSystemR.drawable.ic_capy_overwhelmed);
}

object MoodColorMapping {
    val moodMap = mapOf(
        MoodColor.AQUAMARINE to MoodInfo("Excelente", designSystemR.drawable.ic_capy_serene, MoodType.SERENE),
        MoodColor.DEFAULT to MoodInfo("Bem", designSystemR.drawable.ic_capy_joyful, MoodType.JOYFUL),
        MoodColor.BLUE to MoodInfo("Normal", designSystemR.drawable.ic_capy_balanced, MoodType.BALANCED),
        MoodColor.ORANGE to MoodInfo("Mal", designSystemR.drawable.ic_capy_troubled, MoodType.TROUBLED),
        MoodColor.RED to MoodInfo("Horrível", designSystemR.drawable.ic_capy_overwhelmed, MoodType.OVERWHELMED)
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