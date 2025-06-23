package io.github.faening.lello.core.database.model.journal.sleep

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity
import io.github.faening.lello.core.database.model.option.SleepDurationOptionEntity
import io.github.faening.lello.core.database.model.option.SleepQualityOptionEntity
import io.github.faening.lello.core.database.model.option.SleepSensationOptionEntity
import io.github.faening.lello.core.database.model.option.toModel
import io.github.faening.lello.core.model.journal.SleepJournal

data class SleepJournalEntityWithOptions(
    @Embedded val entry: SleepJournalEntity,

    @Relation(
        parentColumn = "sleepJournalId",
        entityColumn = "locationOptionId",
        associateBy = Junction(
            value = SleepJournalEntityLocationOptionEntityCrossRef::class,
            parentColumn = "sleepJournalId",
            entityColumn = "locationOptionId"
        )
    )
    val locationOptions: List<LocationOptionEntity>,

    @Relation(
        parentColumn = "sleepJournalId",
        entityColumn = "sleepActivityOptionId",
        associateBy = Junction(
            value = SleepJournalEntitySleepActivityOptionEntityCrossRef::class,
            parentColumn = "sleepJournalId",
            entityColumn = "sleepActivityOptionId"
        )
    )
    val sleepActivityOptions: List<SleepActivityOptionEntity>,

    @Relation(
        parentColumn = "sleepJournalId",
        entityColumn = "sleepDurationOptionId",
        associateBy = Junction(
            value = SleepJournalEntitySleepDurationOptionEntityCrossRef::class,
            parentColumn = "sleepJournalId",
            entityColumn = "sleepDurationOptionId"
        )
    )
    val sleepDurationOptions: SleepDurationOptionEntity,

    @Relation(
        parentColumn = "sleepJournalId",
        entityColumn = "sleepQualityOptionId",
        associateBy = Junction(
            value = SleepJournalEntitySleepQualityOptionEntityCrossRef::class,
            parentColumn = "sleepJournalId",
            entityColumn = "sleepQualityOptionId"
        )
    )
    val sleepQualityOptions: List<SleepQualityOptionEntity>,

    @Relation(
        parentColumn = "sleepJournalId",
        entityColumn = "sleepSensationOptionId",
        associateBy = Junction(
            value = SleepJournalEntitySleepSensationOptionEntityCrossRef::class,
            parentColumn = "sleepJournalId",
            entityColumn = "sleepSensationOptionId"
        )
    )
    val sleepSensationOptions: List<SleepSensationOptionEntity>,
)

fun SleepJournalEntityWithOptions.toModel() = SleepJournal(
    id = entry.sleepJournalId,
    sleepDuration = sleepDurationOptions.toModel(),
    sleeplessTime = entry.sleeplessTime,
    sleepSensationOptions = sleepSensationOptions.map { it.toModel() },
    sleepQualityOptions = sleepQualityOptions.map { it.toModel() },
    sleepActivityOptions = sleepActivityOptions.map { it.toModel() },
    locationOptions = locationOptions.map { it.toModel() },
    createdAt = entry.createdAt,
)