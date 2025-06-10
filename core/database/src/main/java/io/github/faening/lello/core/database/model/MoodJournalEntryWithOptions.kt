package io.github.faening.lello.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MoodJournalEntryWithOptions(
    @Embedded val entry: MoodJournalEntryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntryEmotionOptionCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "emotionOptionId"
        )
    )
    val emotionOptions: List<EmotionOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntryClimateOptionCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "climateOptionId"
        )
    )
    val climateOptions: List<ClimateOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntryLocationOptionCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "locationOptionId"
        )
    )
    val locationOptions: List<LocationOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntrySocialOptionCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "socialOptionId"
        )
    )
    val socialOptions: List<SocialOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntryHealthOptionCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "healthOptionId"
        )
    )
    val healthOptions: List<HealthOptionEntity>
)
