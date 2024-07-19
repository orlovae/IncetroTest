package ru.alexandrorlov.incetrotest.data.source.api

import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import javax.inject.Inject

class MainRemoteSource @Inject constructor(private val api: MainApi) {
    suspend fun getOrganizationListRemote(): List<OrganizationRemote> =
        api.getAllOrganizations().organizationsList

    suspend fun getOrganizationById(id: Long): OrganizationDetailRemote =
        api.getOrganizationById(id)

    suspend fun addToFavorite(id: Long) = api.addToFavorite(id)

    suspend fun deleteFromFavorite(id: Long) = api.deleteFromFavorite(id)
}