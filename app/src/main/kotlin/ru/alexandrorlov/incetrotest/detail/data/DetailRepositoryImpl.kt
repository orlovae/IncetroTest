package ru.alexandrorlov.incetrotest.detail.data

import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO
import ru.alexandrorlov.incetrotest.detail.data.source.api.DetailRemoteSource
import ru.alexandrorlov.incetrotest.detail.di.DetailScope
import ru.alexandrorlov.incetrotest.detail.domain.repository.DetailRepository
import timber.log.Timber
import javax.inject.Inject

@DetailScope
class DetailRepositoryImpl @Inject constructor(
    private val remoteSource: DetailRemoteSource,
    private val localSource: OrganizationsDao,
): DetailRepository {

    init {
        Timber.tag("OAE").d("DetailRepository localSource = $localSource")
    }

    override suspend fun getOrganizationById(id: Long): OrganizationsDBO {
        TODO("Not yet implemented")
    }
}