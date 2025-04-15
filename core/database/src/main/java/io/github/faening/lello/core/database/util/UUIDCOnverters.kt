package io.github.faening.lello.core.database.util

import androidx.room.TypeConverter
import java.util.UUID

internal class UUIDCOnverters {
    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return uuid?.let { UUID.fromString(it) }
    }
}