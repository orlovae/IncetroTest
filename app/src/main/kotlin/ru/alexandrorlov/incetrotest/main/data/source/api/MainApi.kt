package ru.alexandrorlov.incetrotest.main.data.source.api

import retrofit2.http.GET
import ru.alexandrorlov.incetrotest.data.network.model.Organizations

interface MainApi {

    @GET("internship/organizations/category/1/organizations/")
    suspend fun getAllOrganizations(): Organizations
}