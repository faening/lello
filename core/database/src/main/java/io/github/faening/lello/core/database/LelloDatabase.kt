package io.github.faening.lello.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.faening.lello.core.database.dao.AppetiteOptionDao
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.FoodOptionDao
import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.dao.ItemCatalogDao
import io.github.faening.lello.core.database.dao.ItemInventoryDao
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.dao.MascotStatusDao
import io.github.faening.lello.core.database.dao.MascotVitalityHistoryDao
import io.github.faening.lello.core.database.dao.MealJournalDao
import io.github.faening.lello.core.database.dao.MealOptionDao
import io.github.faening.lello.core.database.dao.MedicationActiveIngredientOptionDao
import io.github.faening.lello.core.database.dao.MedicationDao
import io.github.faening.lello.core.database.dao.MedicationDosageFormOptionDao
import io.github.faening.lello.core.database.dao.MedicationDosageUnitOptionDao
import io.github.faening.lello.core.database.dao.MedicationJournalDao
import io.github.faening.lello.core.database.dao.MedicationSkipReasonOptionDao
import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.dao.PortionOptionDao
import io.github.faening.lello.core.database.dao.PurchaseHistoryDao
import io.github.faening.lello.core.database.dao.RewardBalanceDao
import io.github.faening.lello.core.database.dao.RewardHistoryDao
import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.dao.SleepJournalDao
import io.github.faening.lello.core.database.dao.SleepQualityOptionDao
import io.github.faening.lello.core.database.dao.SleepSensationOptionDao
import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.database.model.item.ItemCatalogEntity
import io.github.faening.lello.core.database.model.item.ItemInventoryEntity
import io.github.faening.lello.core.database.model.item.PurchaseHistoryEntity
import io.github.faening.lello.core.database.model.journal.JournalCategoryEntity
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntity
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityAppetiteOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityFoodOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityMealOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityPortionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.medication.MedicationJournalEntity
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntity
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntity
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepActivityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepQualityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepSensationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.mascot.MascotStatusEntity
import io.github.faening.lello.core.database.model.mascot.MascotVitalityHistoryEntity
import io.github.faening.lello.core.database.model.medication.MedicationDosageEntity
import io.github.faening.lello.core.database.model.medication.MedicationEntity
import io.github.faening.lello.core.database.model.option.AppetiteOptionEntity
import io.github.faening.lello.core.database.model.option.ClimateOptionEntity
import io.github.faening.lello.core.database.model.option.EmotionOptionEntity
import io.github.faening.lello.core.database.model.option.FoodOptionEntity
import io.github.faening.lello.core.database.model.option.HealthOptionEntity
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.database.model.option.MealOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationActiveIngredientOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationDosageFormOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationSkipReasonOptionEntity
import io.github.faening.lello.core.database.model.option.PortionOptionEntity
import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity
import io.github.faening.lello.core.database.model.option.SleepQualityOptionEntity
import io.github.faening.lello.core.database.model.option.SleepSensationOptionEntity
import io.github.faening.lello.core.database.model.option.SocialOptionEntity
import io.github.faening.lello.core.database.model.reward.RewardBalanceEntity
import io.github.faening.lello.core.database.model.reward.RewardHistoryEntity
import io.github.faening.lello.core.database.util.DateConverter
import io.github.faening.lello.core.database.util.InstantConverters
import io.github.faening.lello.core.database.util.JournalMoodTypeConverter

@Database(
    entities = [
        AppetiteOptionEntity::class,
        ClimateOptionEntity::class,
        EmotionOptionEntity::class,
        FoodOptionEntity::class,
        HealthOptionEntity::class,
        ItemCatalogEntity::class,
        ItemInventoryEntity::class,
        JournalCategoryEntity::class,
        LocationOptionEntity::class,
        MascotStatusEntity::class,
        MascotVitalityHistoryEntity::class,
        MealJournalEntity::class,
        MealJournalEntityAppetiteOptionEntityCrossRef::class,
        MealJournalEntityFoodOptionEntityCrossRef::class,
        MealJournalEntityLocationOptionEntityCrossRef::class,
        MealJournalEntityMealOptionEntityCrossRef::class,
        MealJournalEntityPortionOptionEntityCrossRef::class,
        MealJournalEntitySocialOptionEntityCrossRef::class,
        MedicationJournalEntity::class,
        MealOptionEntity::class,
        MedicationActiveIngredientOptionEntity::class,
        MedicationEntity::class,
        MedicationDosageEntity::class,
        MedicationDosageFormOptionEntity::class,
        MedicationDosageUnitOptionEntity::class,
        MedicationSkipReasonOptionEntity::class,
        MoodJournalEntity::class,
        MoodJournalEntityClimateOptionEntityCrossRef::class,
        MoodJournalEntityEmotionOptionEntityCrossRef::class,
        MoodJournalEntityHealthOptionEntityCrossRef::class,
        MoodJournalEntityLocationOptionEntityCrossRef::class,
        MoodJournalEntitySocialOptionEntityCrossRef::class,
        PortionOptionEntity::class,
        PurchaseHistoryEntity::class,
        RewardBalanceEntity::class,
        RewardHistoryEntity::class,
        SleepActivityOptionEntity::class,
        SleepJournalEntity::class,
        SleepJournalEntityLocationOptionEntityCrossRef::class,
        SleepJournalEntitySleepActivityOptionEntityCrossRef::class,
        SleepJournalEntitySleepQualityOptionEntityCrossRef::class,
        SleepJournalEntitySleepSensationOptionEntityCrossRef::class,
        SleepQualityOptionEntity::class,
        SleepSensationOptionEntity::class,
        SocialOptionEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    DateConverter::class,
    InstantConverters::class,
    JournalMoodTypeConverter::class,
)
abstract class LelloDatabase : RoomDatabase() {
    abstract fun appetiteOptionDao(): AppetiteOptionDao
    abstract fun climateOptionDao(): ClimateOptionDao
    abstract fun emotionOptionDao(): EmotionOptionDao
    abstract fun foodOptionDao(): FoodOptionDao
    abstract fun healthOptionDao(): HealthOptionDao
    abstract fun itemCatalogDao(): ItemCatalogDao
    abstract fun itemInventoryDao(): ItemInventoryDao
    abstract fun journalCategoryDao(): JournalCategoryDao
    abstract fun locationOptionDao(): LocationOptionDao
    abstract fun mascotStatusDao(): MascotStatusDao
    abstract fun mascotVitalityHistoryDao(): MascotVitalityHistoryDao
    abstract fun mealJournalDao(): MealJournalDao
    abstract fun medicationJournalDao(): MedicationJournalDao
    abstract fun mealOptionDao(): MealOptionDao
    abstract fun medicationActiveIngredientOptionDao(): MedicationActiveIngredientOptionDao
    abstract fun medicationDao(): MedicationDao
    abstract fun medicationDosageFormOptionDao(): MedicationDosageFormOptionDao
    abstract fun medicationDosageUnitOptionDao(): MedicationDosageUnitOptionDao
    abstract fun medicationSkipReasonOptionDao(): MedicationSkipReasonOptionDao
    abstract fun moodJournalEntryDao(): MoodJournalDao
    abstract fun portionOptionDao(): PortionOptionDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
    abstract fun rewardBalanceDao(): RewardBalanceDao
    abstract fun rewardHistoryDao(): RewardHistoryDao
    abstract fun sleepActivityOptionDao(): SleepActivityOptionDao
    abstract fun sleepJournalDao(): SleepJournalDao
    abstract fun sleepQualityOptionDao(): SleepQualityOptionDao
    abstract fun sleepSensationOptionDao(): SleepSensationOptionDao
    abstract fun socialOptionDao(): SocialOptionDao
}