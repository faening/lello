package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.AppetiteOptionDao
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.DosageFormOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.FoodOptionDao
import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.dao.MealOptionDao
import io.github.faening.lello.core.database.dao.PortionOptionDao
import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.dao.SensationOptionDao
import io.github.faening.lello.core.database.dao.SleepQualityOptionDao
import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.model.option.ClimateOptionEntity
import io.github.faening.lello.core.database.model.option.DosageFormOptionEntity
import io.github.faening.lello.core.database.model.option.AppetiteOptionEntity
import io.github.faening.lello.core.database.model.option.EmotionOptionEntity
import io.github.faening.lello.core.database.model.option.FoodOptionEntity
import io.github.faening.lello.core.database.model.option.HealthOptionEntity
import io.github.faening.lello.core.database.model.JournalCategoryEntity
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.database.model.option.MealOptionEntity
import io.github.faening.lello.core.database.model.option.PortionOptionEntity
import io.github.faening.lello.core.database.model.moodjournal.MoodJournalEntity
import io.github.faening.lello.core.database.model.moodjournal.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.moodjournal.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.moodjournal.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.moodjournal.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.moodjournal.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.option.SensationOptionEntity
import io.github.faening.lello.core.database.model.option.SleepQualityOptionEntity
import io.github.faening.lello.core.database.model.option.SocialOptionEntity
import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity
import io.github.faening.lello.core.database.util.DateConverter
import io.github.faening.lello.core.database.util.InstantConverters
import io.github.faening.lello.core.database.util.JournalMoodTypeConverter

@Database(
    entities = [
        JournalCategoryEntity::class,
        AppetiteOptionEntity::class,
        ClimateOptionEntity::class,
        DosageFormOptionEntity::class,
        EmotionOptionEntity::class,
        FoodOptionEntity::class,
        HealthOptionEntity::class,
        LocationOptionEntity::class,
        MealOptionEntity::class,
        PortionOptionEntity::class,
        SocialOptionEntity::class,
        SensationOptionEntity::class,
        SleepActivityOptionEntity::class,
        SleepQualityOptionEntity::class,
        SocialOptionEntity::class,
        MoodJournalEntity::class,
        MoodJournalEntityEmotionOptionEntityCrossRef::class,
        MoodJournalEntityClimateOptionEntityCrossRef::class,
        MoodJournalEntityLocationOptionEntityCrossRef::class,
        MoodJournalEntitySocialOptionEntityCrossRef::class,
        MoodJournalEntityHealthOptionEntityCrossRef::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    InstantConverters::class,
    JournalMoodTypeConverter::class,
    DateConverter::class
)
abstract class LelloDatabase : RoomDatabase() {

    // journals
    abstract fun moodJournalEntryDao(): MoodJournalDao
    abstract fun journalCategoryDao(): JournalCategoryDao

    // options
    abstract fun appetiteOptionDao(): AppetiteOptionDao
    abstract fun climateOptionDao(): ClimateOptionDao
    abstract fun dosageFormOptionDao(): DosageFormOptionDao
    abstract fun emotionOptionDao(): EmotionOptionDao
    abstract fun foodOptionDao(): FoodOptionDao
    abstract fun healthOptionDao(): HealthOptionDao
    abstract fun locationOptionDao() : LocationOptionDao
    abstract fun mealOptionDao(): MealOptionDao
    abstract fun portionOptionDao(): PortionOptionDao
    abstract fun sensationOptionDao() : SensationOptionDao
    abstract fun sleepActivityOptionDao() : SleepActivityOptionDao
    abstract fun sleepQualityOptionDao(): SleepQualityOptionDao
    abstract fun socialOptionDao() : SocialOptionDao
}