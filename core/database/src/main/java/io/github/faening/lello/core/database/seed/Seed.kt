package io.github.faening.lello.core.database.seed

interface Seed<T> {
    val data: List<T>
}