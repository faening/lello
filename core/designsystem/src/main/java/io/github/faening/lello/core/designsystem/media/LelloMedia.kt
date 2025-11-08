package io.github.faening.lello.core.designsystem.media

import androidx.annotation.RawRes
import io.github.faening.lello.core.designsystem.R as designsystemR

object LelloMedia {
    object Video {
        val JournalSummaryBackgroundAquamarine = LelloVideo(designsystemR.raw.journal_summay_background_aquamarine)
        val JournalSummaryBackgroundBlue = LelloVideo(designsystemR.raw.journal_summay_background_blue)
        val JournalSummaryBackgroundOrange = LelloVideo(designsystemR.raw.journal_summay_background_orange)
        val JournalSummaryBackgroundRed = LelloVideo(designsystemR.raw.journal_summay_background_red)
        val JournalSummaryBackgroundYellow = LelloVideo(designsystemR.raw.journal_summay_background_yellow)
    }

    // object Audio { ... }
}

data class LelloVideo(@RawRes val resId: Int)