package ru.alexandrorlov.incetrotest.detail.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO

interface DetailRepository {

    suspend fun getOrganizationById(id: Long): Flow<OrganizationDBO>
}