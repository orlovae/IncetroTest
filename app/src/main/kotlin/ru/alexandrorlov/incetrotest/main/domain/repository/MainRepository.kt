package ru.alexandrorlov.incetrotest.main.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO

interface MainRepository {

    suspend fun changeFavorite(id: Long)

    suspend fun getAllOrganizations(): Flow<List<OrganizationsDBO>>

    suspend fun updateDatabase()

    suspend fun getAllFavorite(): Flow<List<OrganizationsDBO>>
}