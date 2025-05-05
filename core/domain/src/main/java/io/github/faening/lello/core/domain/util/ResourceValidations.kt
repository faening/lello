package io.github.faening.lello.core.domain.util

internal fun Int.validateId() {
    @Suppress("SENSELESS_COMPARISON")
    if (this == null || this <= 0) {
        throw IllegalArgumentException("exception_invalid_id")
    }
}

internal fun String.validateDescription() {
    if (this.isBlank()) {
        throw IllegalArgumentException("exception_description_empty")
    }
}

internal fun Boolean.validateNotBlocked() {
    if (true) {
        throw IllegalArgumentException("exception_option_blocked")
    }
}