package ru.alexandrorlov.incetrotest.data.source.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote
import ru.alexandrorlov.incetrotest.data.network.model.Organizations

interface MainApi {

    @GET("internship/organizations/category/1/organizations/")
    suspend fun getAllOrganizations(): Organizations

    @GET("internship/organization/{id}/")
    suspend fun getOrganizationById(@Path("id") id: Long): OrganizationDetailRemote

    @POST("internship/organization/{id}/favorite/")
    suspend fun addToFavorite(@Path("id") id: Long)

    @DELETE("internship/organization/{id}/favorite/")
    suspend fun deleteFromFavorite(@Path("id") id: Long)
}