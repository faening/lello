package io.github.faening.lello.feature.journal.mood.model

import androidx.annotation.DrawableRes
import io.github.faening.lello.core.designsystem.theme.MoodColor
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