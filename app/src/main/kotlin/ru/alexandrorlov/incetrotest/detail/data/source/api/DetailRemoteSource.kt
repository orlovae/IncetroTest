package ru.alexandrorlov.incetrotest.detail.data.source.api

import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote
import ru.alexandrorlov.incetrotest.detail.di.DetailScope
import javax.inject.Inject

@DetailScope
class DetailRemoteSource @Inject constructor(
    private val detailApi: DetailApi,
) {

    suspend fun getDetailOrganizationById(id: Long): OrganizationDetailRemote =
        detailApi.getDetailOrganizationById(id)
}