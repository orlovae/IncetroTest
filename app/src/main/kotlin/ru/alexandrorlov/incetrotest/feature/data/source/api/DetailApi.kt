package ru.alexandrorlov.incetrotest.feature.data.source.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote

interface DetailApi {

    @GET("internship/organization/{id}/")
    suspend fun getDetailOrganizationById(@Path("id") id: Long): OrganizationDetailRemote
}