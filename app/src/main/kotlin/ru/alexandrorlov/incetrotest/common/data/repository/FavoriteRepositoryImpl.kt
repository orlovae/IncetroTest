package ru.alexandrorlov.incetrotest.common.data.repository

import ru.alexandrorlov.incetrotest.common.data.source.api.FavoriteRemoteSource
import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.di.AppScope
import javax.inject.Inject

@AppScope
class FavoriteRepositoryImpl @Inject constructor(
    private val remoteSource: FavoriteRemoteSource,
    private val localSource: OrganizationsDao,
    ) : FavoriteRepository {

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
}