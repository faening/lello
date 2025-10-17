package io.github.faening.lello.core.model.notification

data class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val hour: Int,
    val minute: Int
)
