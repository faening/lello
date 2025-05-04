package io.github.faening.lello.core.domain.util

fun String.capitalizeFirst(): String {
    return this
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}