package ru.alexandrorlov.incetrotest.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import ru.alexandrorlov.incetrotest.data.source.api.MainRemoteSource
import ru.alexandrorlov.incetrotest.main.domain.mapper.toDB
import ru.alexandrorlov.incetrotest.main.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteSource: MainRemoteSource,
    private val localSource: OrganizationsDao,
): MainRepository {

    override suspend fun changeFavorite(id: Long) {
        val clickOrganization = localSource.getOrganizationById(id)
        val isFavorite = clickOrganization.isFavorite
        val newClickOrganization: OrganizationsDBO = clickOrganization.copy(isFavorite = isFavorite.not())

        localSource.insertOrganization(newClickOrganization)

        if (isFavorite.not()) {
            remoteSource.addToFavorite(id)
        } else {
            remoteSource.deleteFromFavorite(id)
        }
    }

    override suspend fun getAllOrganizations(): Flow<List<OrganizationsDBO>> =
        localSource.getAll()
            .onEach { list ->
                list.ifEmpty {
                    val listDBO = remoteSource.getOrganizationListRemote().map {
                        it.toDB()
                    }
                    localSource.clean()
                    localSource.insertAll(listDBO)
                }
            }

    override suspend fun updateDatabase() {
        val listRemote: List<OrganizationRemote> = remoteSource.getOrganizationListRemote()

        localSource.clean()
        localSource.insertAll(listRemote.map { it.toDB() })
    }

    override suspend fun getAllFavorite(): Flow<List<OrganizationsDBO>> =
        localSource.getAllFavorite()
}