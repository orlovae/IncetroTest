package ru.alexandrorlov.incetrotest.common.data.source.api

import ru.alexandrorlov.incetrotest.di.AppScope
import javax.inject.Inject

@AppScope
class FavoriteRemoteSource @Inject constructor(
    private val favoriteApi: FavoriteApi
) {

    suspend fun addToFavorite(id: Long) = favoriteApi.addToFavorite(id)

    suspend fun deleteFromFavorite(id: Long) = favoriteApi.deleteFromFavorite(id)
}