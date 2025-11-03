package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationActiveIngredientOptionDao
import io.github.faening.lello.core.database.model.option.MedicationActiveIngredientOptionEntity
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import jakarta.inject.Inject

class DataMedicationActiveIngredientOptionRepository @Inject constructor(
    dao: MedicationActiveIngredientOptionDao
) : DataAbstractOptionRepository<MedicationActiveIngredientOption, MedicationActiveIngredientOptionEntity>(dao) {

    override fun MedicationActiveIngredientOptionEntity.toModel(): MedicationActiveIngredientOption {
        return MedicationActiveIngredientOption(
            id = this.activeIngredientOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun MedicationActiveIngredientOption.toEntity(): MedicationActiveIngredientOptionEntity {
        return MedicationActiveIngredientOptionEntity(
            activeIngredientOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}