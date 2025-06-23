package io.github.faening.lello.core.database.model.journal.mood

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.*
import io.github.faening.lello.core.model.journal.MoodJournal

data class MoodJournalEntityWithOptions(
    @Embedded val entry: MoodJournalEntity,
    @Relation(
        parentColumn = "moodJournalId",
        entityColumn = "emotionOptionId",
        associateBy = Junction(
            value = MoodJournalEntityEmotionOptionEntityCrossRef::class,
            parentColumn = "moodJournalId",
            entityColumn = "emotionOptionId"
        )
    )
    val emotionOptions: List<EmotionOptionEntity>,

    @Relation(
        parentColumn = "moodJournalId",
        entityColumn = "climateOptionId",
        associateBy = Junction(
            value = MoodJournalEntityClimateOptionEntityCrossRef::class,
            parentColumn = "moodJournalId",
            entityColumn = "climateOptionId"
        )
    )
    val climateOptions: List<ClimateOptionEntity>,

    @Relation(
        parentColumn = "moodJournalId",
        entityColumn = "locationOptionId",
        associateBy = Junction(
            value = MoodJournalEntityLocationOptionEntityCrossRef::class,
            parentColumn = "moodJournalId",
            entityColumn = "locationOptionId"
        )
    )
    val locationOptions: List<LocationOptionEntity>,

    @Relation(
        parentColumn = "moodJournalId",
        entityColumn = "socialOptionId",
        associateBy = Junction(
            value = MoodJournalEntitySocialOptionEntityCrossRef::class,
            parentColumn = "moodJournalId",
            entityColumn = "socialOptionId"
        )
    )
    val socialOptions: List<SocialOptionEntity>,

    @Relation(
        parentColumn = "moodJournalId",
        entityColumn = "healthOptionId",
        associateBy = Junction(
            value = MoodJournalEntityHealthOptionEntityCrossRef::class,
            parentColumn = "moodJournalId",
            entityColumn = "healthOptionId"
        )
    )
    val healthOptions: List<HealthOptionEntity>
)

fun MoodJournalEntityWithOptions.toModel() = MoodJournal(
    id = entry.moodJournalId,
    mood = entry.mood,
    reflection = entry.reflection,
    emotionOptions = emotionOptions.map { it.toModel() },
    climateOptions = climateOptions.map { it.toModel() },
    locationOptions = locationOptions.map { it.toModel() },
    socialOptions = socialOptions.map { it.toModel() },
    healthOptions = healthOptions.map { it.toModel() },
    createdAt = entry.createdAt,
)