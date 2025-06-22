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
import io.github.faening.lello.core.database.dao.MealJournalDao
import io.github.faening.lello.core.database.dao.MealOptionDao
import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.dao.PortionOptionDao
import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.dao.SleepDurationOptionDao
import io.github.faening.lello.core.database.dao.SleepJournalDao
import io.github.faening.lello.core.database.dao.SleepQualityOptionDao
import io.github.faening.lello.core.database.dao.SleepSensationOptionDao
import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.database.model.journal.JournalCategoryEntity
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntity
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityAppetiteOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityFoodOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityMealOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityPortionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntity
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntity
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepActivityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepDurationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepQualityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepSensationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.option.AppetiteOptionEntity
import io.github.faening.lello.core.database.model.option.ClimateOptionEntity
import io.github.faening.lello.core.database.model.option.DosageFormOptionEntity
import io.github.faening.lello.core.database.model.option.EmotionOptionEntity
import io.github.faening.lello.core.database.model.option.FoodOptionEntity
import io.github.faening.lello.core.database.model.option.HealthOptionEntity
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.database.model.option.MealOptionEntity
import io.github.faening.lello.core.database.model.option.PortionOptionEntity
import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity
import io.github.faening.lello.core.database.model.option.SleepDurationOptionEntity
import io.github.faening.lello.core.database.model.option.SleepQualityOptionEntity
import io.github.faening.lello.core.database.model.option.SleepSensationOptionEntity
import io.github.faening.lello.core.database.model.option.SocialOptionEntity
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
        SleepActivityOptionEntity::class,
        SleepDurationOptionEntity::class,
        SleepQualityOptionEntity::class,
        SleepSensationOptionEntity::class,
        SocialOptionEntity::class,
        SleepJournalEntity::class,
        SleepJournalEntityLocationOptionEntityCrossRef::class,
        SleepJournalEntitySleepActivityOptionEntityCrossRef::class,
        SleepJournalEntitySleepDurationOptionEntityCrossRef::class,
        SleepJournalEntitySleepSensationOptionEntityCrossRef::class,
        SleepJournalEntitySleepQualityOptionEntityCrossRef::class,
        MoodJournalEntity::class,
        MoodJournalEntityEmotionOptionEntityCrossRef::class,
        MoodJournalEntityClimateOptionEntityCrossRef::class,
        MoodJournalEntityLocationOptionEntityCrossRef::class,
        MoodJournalEntitySocialOptionEntityCrossRef::class,
        MoodJournalEntityHealthOptionEntityCrossRef::class,
        MealJournalEntity::class,
        MealJournalEntityMealOptionEntityCrossRef::class,
        MealJournalEntityAppetiteOptionEntityCrossRef::class,
        MealJournalEntityFoodOptionEntityCrossRef::class,
        MealJournalEntityPortionOptionEntityCrossRef::class,
        MealJournalEntityLocationOptionEntityCrossRef::class,
        MealJournalEntitySocialOptionEntityCrossRef::class
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
    abstract fun mealJournalDao(): MealJournalDao
    abstract fun sleepJournalDao(): SleepJournalDao
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
    abstract fun sleepActivityOptionDao() : SleepActivityOptionDao
    abstract fun sleepDurationOptionDao() : SleepDurationOptionDao
    abstract fun sleepQualityOptionDao(): SleepQualityOptionDao
    abstract fun sleepSensationOptionDao() : SleepSensationOptionDao
    abstract fun socialOptionDao() : SocialOptionDao
}