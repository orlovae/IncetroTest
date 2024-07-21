package ru.alexandrorlov.incetrotest.common.data.source.api

import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteApi {

    @POST("internship/organization/{id}/favorite/")
    suspend fun addToFavorite(@Path("id") id: Long)

    @DELETE("internship/organization/{id}/favorite/")
    suspend fun deleteFromFavorite(@Path("id") id: Long)
}