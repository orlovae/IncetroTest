package ru.alexandrorlov.incetrotest.feature.data.source.api

import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import ru.alexandrorlov.incetrotest.feature.di.FeatureScope
import javax.inject.Inject

@FeatureScope
class MainRemoteSource @Inject constructor(private val mainApi: MainApi) {

    suspend fun getOrganizationListRemote(): List<OrganizationRemote> =
        mainApi.getAllOrganizations().organizationsList
}