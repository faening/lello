package io.github.faening.lello.core.designsystem.icon

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import io.github.faening.lello.core.designsystem.R as designsystemR

object LelloIcons {
    object Graphic {
        val Logotipo = LelloIcon(designsystemR.drawable.ic_lello_logotipo_custom)
        val Logo = LelloIcon(designsystemR.drawable.ic_lello_logo_custom)

        // Achievements
        val AchievementsBackgroundForest = LelloIcon(designsystemR.drawable.img_achievements_background_forest)
        val AchievementsMascotLelloBard = LelloIcon(designsystemR.drawable.img_achievements_mascot_lello_bard)
        val AchievementsInventory = LelloIcon(designsystemR.drawable.ic_achievements_inventory_custom)
        val AchievementsShop = LelloIcon(designsystemR.drawable.ic_achievements_shop_custom)

        // Card - Check-in Daily
        val BonusHexagon = LelloIcon(designsystemR.drawable.img_bonus_hexagon_10)

        // Card - Diary
        val Coin = LelloIcon(designsystemR.drawable.ic_coin_custom)
        val Heart = LelloIcon(designsystemR.drawable.ic_heart_custom)

        // Diaries
        val DiaryMeal = LelloIcon(designsystemR.drawable.img_diary_meal_screen)
        val DiaryMedication = LelloIcon(designsystemR.drawable.img_diary_medication_screen)
        val DiaryMood = LelloIcon(designsystemR.drawable.img_diary_mood_screen)
        val DiarySleep = LelloIcon(designsystemR.drawable.img_diary_sleep_screen)

        // Journals
        val JournalMeal = LelloIcon(designsystemR.drawable.ic_journal_meal_custom)
        val JournalMedication = LelloIcon(designsystemR.drawable.ic_journal_medication_custom)
        val JournalMood = LelloIcon(designsystemR.drawable.ic_journal_mood_custom)
        val JournalSleep = LelloIcon(designsystemR.drawable.ic_journal_sleep_custom)
        val JournalSummary = LelloIcon(designsystemR.drawable.img_journal_summary)

        // Capybaras
        val CapybaraBalanced = LelloIcon(designsystemR.drawable.ic_capybara_balanced_custom)
        val CapybaraJoyful = LelloIcon(designsystemR.drawable.ic_capybara_joyful_custom)
        val CapybaraOverwhelmed = LelloIcon(designsystemR.drawable.ic_capybara_overwhelmed_custom)
        val CapybaraSerene = LelloIcon(designsystemR.drawable.ic_capybara_serene_custom)
        val CapybaraTroubled = LelloIcon(designsystemR.drawable.ic_capybara_troubled_custom)
    }

    object Outlined {
        val Add = LelloIcon(designsystemR.drawable.ic_plus_add_outlined)
        val ArrowUpLarge = LelloIcon(designsystemR.drawable.ic_arrow_up_lg_outlined)
        val ArrowUpSmall = LelloIcon(designsystemR.drawable.ic_arrow_up_sm_outlined)
        val ArrowRightLarge = LelloIcon(designsystemR.drawable.ic_arrow_right_lg_outlined)
        val ArrowRightSmall = LelloIcon(designsystemR.drawable.ic_arrow_right_sm_outlined)
        val ArrowLeftLarge = LelloIcon(designsystemR.drawable.ic_arrow_left_lg_outlined)
        val ArrowLeftSmall = LelloIcon(designsystemR.drawable.ic_arrow_left_sm_outlined)
        val ArrowDownLarge = LelloIcon(designsystemR.drawable.ic_arrow_down_lg_outlined)
        val ArrowDownSmall = LelloIcon(designsystemR.drawable.ic_arrow_down_sm_outlined)
        val ArrowTopLeftRound = LelloIcon(designsystemR.drawable.ic_arrow_top_left_round_outlined)
        val BookOpen = LelloIcon(designsystemR.drawable.ic_book_open_outlined)
        val Blocked = LelloIcon(designsystemR.drawable.ic_blocked_outlined)
        val Calendar = LelloIcon(designsystemR.drawable.ic_calendar_outlined)
        val ChevronUp = LelloIcon(designsystemR.drawable.ic_chevron_up_outlined)
        val ChevronRight = LelloIcon(designsystemR.drawable.ic_chevron_right_outlined)
        val ChevronDown = LelloIcon(designsystemR.drawable.ic_chevron_down_outlined)
        val ChevronLeft = LelloIcon(designsystemR.drawable.ic_chevron_left_outlined)
        val ChevronMirrored = LelloIcon(designsystemR.drawable.ic_chevron_mirrored_outlined)
        val Clock = LelloIcon(designsystemR.drawable.ic_clock_outlined)
        val Close = LelloIcon(designsystemR.drawable.ic_close_outlined)
        val CloseRound = LelloIcon(designsystemR.drawable.ic_close_round_outlined)
        val Document = LelloIcon(designsystemR.drawable.ic_document_outlined)
        val Download = LelloIcon(designsystemR.drawable.ic_download_outlined)
        val DrugPill = LelloIcon(designsystemR.drawable.ic_drug_pill_long_outlined)
        val Edit = LelloIcon(designsystemR.drawable.ic_edit_outlined)
        val Fingerprint = LelloIcon(designsystemR.drawable.ic_fingerprint_outlined)
        val Google = LelloIcon(designsystemR.drawable.ic_google_outlined)
        val Heart = LelloIcon(designsystemR.drawable.ic_heart_outlined)
        val Home = LelloIcon(designsystemR.drawable.ic_home_outlined)
        val Key = LelloIcon(designsystemR.drawable.ic_key_outlined)
        val Lock = LelloIcon(designsystemR.drawable.ic_lock_outlined)
        val Mail = LelloIcon(designsystemR.drawable.ic_mail_outlined)
        val Menu = LelloIcon(designsystemR.drawable.ic_menu_outlined)
        val MoreVertical = LelloIcon(designsystemR.drawable.ic_more_vertical_outlined)
        val NotificationBell = LelloIcon(designsystemR.drawable.ic_notification_bell_outlined)
        val Profile = LelloIcon(designsystemR.drawable.ic_profile_outlined)
        val Remove = LelloIcon(designsystemR.drawable.ic_plus_remove_outlined)
        val Search = LelloIcon(designsystemR.drawable.ic_search_outlined)
        val Settings = LelloIcon(designsystemR.drawable.ic_settings_outlined)
        val Sound = LelloIcon(designsystemR.drawable.ic_sound_outlined)
        val SoundOff = LelloIcon(designsystemR.drawable.ic_sound_off_outlined)
        val Sum = LelloIcon(designsystemR.drawable.ic_sum_outlined)
        val Trash = LelloIcon(designsystemR.drawable.ic_trash_outlined)
        val Verified = LelloIcon(designsystemR.drawable.ic_verified_outlined)
    }

    object Filled {
        val ArrowTopLeftRound = LelloIcon(designsystemR.drawable.ic_arrow_top_left_round_filled)
        val BookOpen = LelloIcon(designsystemR.drawable.ic_book_open_filled)
        val Calendar = LelloIcon(designsystemR.drawable.ic_calendar_filled)
        val Capybara = LelloIcon(designsystemR.drawable.ic_capybara_filled)
        val Clock = LelloIcon(designsystemR.drawable.ic_clock_filled)
        val CloseRound = LelloIcon(designsystemR.drawable.ic_close_round_filled)
        val Document = LelloIcon(designsystemR.drawable.ic_document_filled)
        val DrugPill = LelloIcon(designsystemR.drawable.ic_drug_pill_long_filled)
        val Google = LelloIcon(designsystemR.drawable.ic_google_outlined)
        val Heart = LelloIcon(designsystemR.drawable.ic_heart_filled)
        val Home = LelloIcon(designsystemR.drawable.ic_home_filled)
        val Key = LelloIcon(designsystemR.drawable.ic_key_filled)
        val Lock = LelloIcon(designsystemR.drawable.ic_lock_filled)
        val Mail = LelloIcon(designsystemR.drawable.ic_mail_filled)
        val Menu = LelloIcon(designsystemR.drawable.ic_menu_filled)
        val MoreVertical = LelloIcon(designsystemR.drawable.ic_more_vertical_filled)
        val NotificationBell = LelloIcon(designsystemR.drawable.ic_notification_bell_filled)
        val Profile = LelloIcon(designsystemR.drawable.ic_profile_filled)
        val Search = LelloIcon(designsystemR.drawable.ic_search_filled)
        val Settings = LelloIcon(designsystemR.drawable.ic_settings_filled)
        val Sound = LelloIcon(designsystemR.drawable.ic_sound_filled)
        val SoundOff = LelloIcon(designsystemR.drawable.ic_sound_off_filled)
        val Sum = LelloIcon(designsystemR.drawable.ic_sum_filled)
        val Trash = LelloIcon(designsystemR.drawable.ic_trash_filled)
        val Verified = LelloIcon(designsystemR.drawable.ic_verified_filled)
    }

    @Composable
    fun customIcon(id: Int): ImageVector = ImageVector.vectorResource(id)
}

data class LelloIcon(val resId: Int) {
    val imageVector: ImageVector
        @Composable get() = ImageVector.vectorResource(resId)
}

/**
 * Um Composable que carrega dinamicamente um ícone com base no seu NOME (String).
 *
 * @param resourceName O nome do recurso drawable.
 * @param contentDescription A descrição do conteúdo para acessibilidade.
 * @param modifier Modificador para estilizar o Image Composable.
 */
@Composable
fun DynamicLelloIcon(
    resourceName: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val resourceId = remember(resourceName, context) {
        context.resources.getIdentifier(
            resourceName,
            "drawable",
            context.packageName
        )
    }

    if (resourceId != 0) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = contentDescription,
            modifier = modifier
        )
    } else {
        // Você pode substituir por um ícone de placeholder do LelloIcons
        Image(
            imageVector = Icons.Default.BrokenImage, // Substituir
            contentDescription = "Imagem não encontrada",
            modifier = modifier
        )
    }
}