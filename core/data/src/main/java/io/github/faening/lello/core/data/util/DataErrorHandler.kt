package io.github.faening.lello.core.data.util

import android.database.sqlite.SQLiteException
import android.net.http.NetworkException
import android.os.Build
import androidx.annotation.RequiresExtension

sealed class DataError(open val message: String) {
    data class NetworkError(override val message: String = "Erro de rede") : DataError(message)
    data class DatabaseError(override val message: String = "Erro no banco de dados") : DataError(message)
    data class UnknownError(override val message: String = "Erro desconhecido") : DataError(message)
}

object DataErrorHandler {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun handleError(throwable: Throwable): DataError {
        return when (throwable) {
            is NetworkException -> DataError.NetworkError()
            is SQLiteException -> DataError.DatabaseError()
            else -> DataError.UnknownError()
        }
    }
}