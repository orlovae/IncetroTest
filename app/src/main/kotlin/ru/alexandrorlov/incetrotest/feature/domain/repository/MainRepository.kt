package ru.alexandrorlov.incetrotest.feature.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO

interface MainRepository {

    suspend fun getAllOrganizations(): Flow<List<OrganizationDBO>>

    suspend fun updateDatabase()
}