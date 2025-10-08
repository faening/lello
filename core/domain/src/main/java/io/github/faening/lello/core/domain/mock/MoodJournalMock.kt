package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.MoodType

object MoodJournalMock {
    val list = listOf(
        MoodJournal(
            id = 1,
            mood = MoodType.SERENE,
            reflection = "Reflexão 1",
            emotionOptions = EmotionOptionMock.list.take(1),
            climateOptions = ClimateOptionMock.list.take(1),
            locationOptions = LocationOptionMock.list.take(1),
            socialOptions = SocialOptionMock.list.take(1),
            healthOptions = HealthOptionMock.list.take(1),
            createdAt = System.currentTimeMillis()
        ),
        MoodJournal(
            id = 2,
            mood = MoodType.JOYFUL,
            reflection = "Reflexão 2",
            emotionOptions = EmotionOptionMock.list.take(2),
            climateOptions = ClimateOptionMock.list.take(2),
            locationOptions = LocationOptionMock.list.take(2),
            socialOptions = SocialOptionMock.list.take(2),
            healthOptions = HealthOptionMock.list.take(2),
            createdAt = System.currentTimeMillis()
        ),
        MoodJournal(
            id = 3,
            mood = MoodType.BALANCED,
            reflection = "Reflexão 3",
            emotionOptions = EmotionOptionMock.list.take(3),
            climateOptions = ClimateOptionMock.list.take(3),
            locationOptions = LocationOptionMock.list.take(3),
            socialOptions = SocialOptionMock.list.take(3),
            healthOptions = HealthOptionMock.list.take(3),
            createdAt = System.currentTimeMillis()
        ),
        MoodJournal(
            id = 4,
            mood = MoodType.TROUBLED,
            reflection = "Reflexão 4",
            emotionOptions = EmotionOptionMock.list.take(4),
            climateOptions = ClimateOptionMock.list.take(4),
            locationOptions = LocationOptionMock.list.take(4),
            socialOptions = SocialOptionMock.list.take(4),
            healthOptions = HealthOptionMock.list.take(4),
            createdAt = System.currentTimeMillis()
        ),
        MoodJournal(
            id = 5,
            mood = MoodType.OVERWHELMED,
            reflection = "Reflexão 5",
            emotionOptions = EmotionOptionMock.list.take(5),
            climateOptions = ClimateOptionMock.list.take(5),
            locationOptions = LocationOptionMock.list.take(5),
            socialOptions = SocialOptionMock.list.take(5),
            healthOptions = HealthOptionMock.list.take(5),
            createdAt = System.currentTimeMillis()
        )
    )
}
