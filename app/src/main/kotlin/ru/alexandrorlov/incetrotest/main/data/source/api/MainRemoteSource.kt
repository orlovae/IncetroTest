package ru.alexandrorlov.incetrotest.main.data.source.api

import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import ru.alexandrorlov.incetrotest.main.di.MainScope
import javax.inject.Inject

@MainScope
class MainRemoteSource @Inject constructor(private val mainApi: MainApi) {

    suspend fun getOrganizationListRemote(): List<OrganizationRemote> =
        mainApi.getAllOrganizations().organizationsList
}