package ru.alexandrorlov.incetrotest.feature.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationDetailRemote
import ru.alexandrorlov.incetrotest.feature.data.source.api.DetailRemoteSource
import ru.alexandrorlov.incetrotest.feature.di.FeatureScope
import ru.alexandrorlov.incetrotest.feature.domain.mapper.toDB
import ru.alexandrorlov.incetrotest.feature.domain.repository.DetailRepository
import javax.inject.Inject

@FeatureScope
class DetailRepositoryImpl @Inject constructor(
    private val remoteSource: DetailRemoteSource,
    private val localSource: OrganizationsDao,
): DetailRepository {

    override suspend fun getOrganizationById(id: Long): Flow<OrganizationDBO> =
        localSource.getOrganizationById(id)
            .distinctUntilChanged()
            .onEach {
                updateOrganizationFromRemote(id, it.photo)
            }

    private suspend fun updateOrganizationFromRemote(id: Long, photo: String) {
        val organization: OrganizationDetailRemote = remoteSource.getDetailOrganizationById(id)

        localSource.insertOrganization(organization.toDB(photo))
    }
}