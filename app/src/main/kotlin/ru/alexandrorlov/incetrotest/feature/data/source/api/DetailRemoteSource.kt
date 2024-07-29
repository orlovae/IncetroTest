package ru.alexandrorlov.incetrotest.feature.data.source.api

import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote
import ru.alexandrorlov.incetrotest.feature.di.FeatureScope
import javax.inject.Inject

@FeatureScope
class DetailRemoteSource @Inject constructor(
    private val detailApi: DetailApi,
) {

    suspend fun getDetailOrganizationById(id: Long): OrganizationDetailRemote =
        detailApi.getDetailOrganizationById(id)
}