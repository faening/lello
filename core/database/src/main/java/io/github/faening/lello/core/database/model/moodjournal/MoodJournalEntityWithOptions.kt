package io.github.faening.lello.core.database.model.moodjournal

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.ClimateOptionEntity
import io.github.faening.lello.core.database.model.option.EmotionOptionEntity
import io.github.faening.lello.core.database.model.option.HealthOptionEntity
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.database.model.option.SocialOptionEntity

data class MoodJournalEntityWithOptions(
    @Embedded val entry: MoodJournalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntityEmotionOptionEntityCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "emotionOptionId"
        )
    )
    val emotionOptions: List<EmotionOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntityClimateOptionEntityCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "climateOptionId"
        )
    )
    val climateOptions: List<ClimateOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntityLocationOptionEntityCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "locationOptionId"
        )
    )
    val locationOptions: List<LocationOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntitySocialOptionEntityCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "socialOptionId"
        )
    )
    val socialOptions: List<SocialOptionEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = MoodJournalEntityHealthOptionEntityCrossRef::class,
            parentColumn = "entryId",
            entityColumn = "healthOptionId"
        )
    )
    val healthOptions: List<HealthOptionEntity>
)