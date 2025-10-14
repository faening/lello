package io.github.faening.lello.core.database.model.journal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.JournalCategory

@Entity(tableName = "journal_categories")
data class JournalCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    @ColumnInfo(name = "short_description") val shortDescription: String,
    @ColumnInfo(name = "long_description") val longDescription: String,
    val blocked: Boolean,
    val active: Boolean,
)

fun JournalCategoryEntity.toModel() = JournalCategory(
    id = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
    blocked = blocked,
    active = active
)

fun JournalCategory.toEntity() = JournalCategoryEntity(
    id = this.id ?: 0,
    name = this.name,
    shortDescription = this.shortDescription,
    longDescription = this.longDescription,
    blocked = this.blocked,
    active = this.active
)