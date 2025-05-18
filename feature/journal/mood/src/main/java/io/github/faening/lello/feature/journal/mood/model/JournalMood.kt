package io.github.faening.lello.feature.journal.mood.model

import androidx.annotation.DrawableRes
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.R as designSystemR

enum class JournalMood(
    val label: String,
    val colorScheme: LelloColorScheme,
    @DrawableRes val iconRes: Int
) {
    SERENE      ("Excelente", LelloColorScheme.AQUAMARINE, designSystemR.drawable.ic_capy_serene),
    JOYFUL      ("Bem",       LelloColorScheme.DEFAULT,    designSystemR.drawable.ic_capy_joyful),
    BALANCED    ("Normal",    LelloColorScheme.BLUE,       designSystemR.drawable.ic_capy_balanced),
    TROUBLED    ("Mal",       LelloColorScheme.ORANGE,     designSystemR.drawable.ic_capy_troubled),
    OVERWHELMED ("Horrível",  LelloColorScheme.RED,        designSystemR.drawable.ic_capy_overwhelmed);
}