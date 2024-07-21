package ru.alexandrorlov.incetrotest.common.domain.repository

interface FavoriteRepository {
    suspend fun changeFavorite(id: Long)
}