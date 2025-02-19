package ru.alexandrorlov.incetrotest.feature.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO
import ru.alexandrorlov.incetrotest.data.network.model.OrganizationRemote
import ru.alexandrorlov.incetrotest.feature.data.source.api.MainRemoteSource
import ru.alexandrorlov.incetrotest.feature.di.FeatureScope
import ru.alexandrorlov.incetrotest.feature.domain.mapper.toDB
import ru.alexandrorlov.incetrotest.feature.domain.repository.MainRepository
import javax.inject.Inject

@FeatureScope
class MainRepositoryImpl @Inject constructor(
    private val remoteSource: MainRemoteSource,
    private val localSource: OrganizationsDao,
): MainRepository {

    override suspend fun getAllOrganizations(): Flow<List<OrganizationDBO>> =
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
}