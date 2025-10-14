package io.github.faening.lello.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import io.github.faening.lello.core.designsystem.R as designsystemR

object LelloIcons {

    // Base Icons
    val Add = Icons.Rounded.Add
    val ArrowLargeUp = LelloIcon(designsystemR.drawable.ic_arrow_large_up)
    val ArrowLargeRight = LelloIcon(designsystemR.drawable.ic_arrow_large_right)
    val ArrowLargeLeft = LelloIcon(designsystemR.drawable.ic_arrow_large_left)
    val ArrowLargeDown = LelloIcon(designsystemR.drawable.ic_arrow_large_down)
    val ChevronUp = LelloIcon(designsystemR.drawable.ic_chevron_up)
    val ChevronRight = LelloIcon(designsystemR.drawable.ic_chevron_right)
    val ChevronDown = LelloIcon(designsystemR.drawable.ic_chevron_down)
    val ChevronLeft = LelloIcon(designsystemR.drawable.ic_chevron_left)
    val Favorite = Icons.Rounded.Favorite
    val Logo = LelloIcon(designsystemR.drawable.ic_lello_logo)
    val MoreVert = Icons.Rounded.MoreVert

    object Graphic {
        // Card - Check-in Daily
        val BonusHexagon = LelloIcon(designsystemR.drawable.ic_bonus_hexagon_10)

        // Card - Diary
        val Coin = LelloIcon(designsystemR.drawable.ic_coin)
        val JournalMeal = LelloIcon(designsystemR.drawable.ic_journal_meal)
        val JournalMedication = LelloIcon(designsystemR.drawable.ic_journal_medication)
        val JournalMood = LelloIcon(designsystemR.drawable.ic_journal_mood)
        val JournalMoodSerene = LelloIcon(designsystemR.drawable.ic_capy_serene)
        val JournalMoodJoyful = LelloIcon(designsystemR.drawable.ic_capy_joyful)
        val JournalMoodBalanced = LelloIcon(designsystemR.drawable.ic_capy_balanced)
        val JournalMoodTroubled = LelloIcon(designsystemR.drawable.ic_capy_troubled)
        val JournalMoodOverwhelmed = LelloIcon(designsystemR.drawable.ic_capy_overwhelmed)
        val JournalSleep = LelloIcon(designsystemR.drawable.ic_journal_sleep)

        // Journals
        val MoodSummary = LelloIcon(designsystemR.drawable.journal_summary)
    }

    object Outlined {
        val Achievement = LelloIcon(designsystemR.drawable.ic_achievements)
        val BookOpen = LelloIcon(designsystemR.drawable.ic_book_open_outlined)
        val Calendar = LelloIcon(designsystemR.drawable.ic_outlined_calendar)
        val DrugPill = LelloIcon(designsystemR.drawable.ic_drug_pill_long_outlined)
        val Fingerprint = Icons.Outlined.Fingerprint
        val Home = LelloIcon(designsystemR.drawable.ic_home_outlined)
        val Lock = LelloIcon(designsystemR.drawable.ic_lock_outlined)
        val Mail = LelloIcon(designsystemR.drawable.ic_mail_outlined)
        val Profile = LelloIcon(designsystemR.drawable.ic_profile_outlined)
    }

    object Filled {
        val Achievement = LelloIcon(designsystemR.drawable.ic_achievements)
        val BookOpen = LelloIcon(designsystemR.drawable.ic_book_open_filled)
        val DrugPill = LelloIcon(designsystemR.drawable.ic_drug_pill_long_filled)
        val Fingerprint = Icons.Filled.Fingerprint
        val Google = LelloIcon(designsystemR.drawable.ic_google_filled)
        val Home = LelloIcon(designsystemR.drawable.ic_home_filled)
        val Lock = LelloIcon(designsystemR.drawable.ic_lock_filled)
        val Profile = LelloIcon(designsystemR.drawable.ic_profile_filled)
    }

    @Composable
    fun customIcon(id: Int): ImageVector = ImageVector.vectorResource(id)
}

data class LelloIcon(val resId: Int) {
    val imageVector: ImageVector
        @Composable get() = ImageVector.vectorResource(resId)
}