package ru.alexandrorlov.incetrotest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationDBO

@Dao
interface OrganizationsDao {

    @Query("SELECT * FROM organizations")
    fun getAll(): Flow<List<OrganizationDBO>>

    @Query("SELECT * FROM organizations WHERE organizations.isFavorite = 1")
    fun getAllFavorite(): Flow<List<OrganizationDBO>>

    @Query("SELECT * FROM organizations WHERE organizations.id = :id")
    fun getOrganizationById(id: Long): Flow<OrganizationDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(organizations: List<OrganizationDBO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrganization(organization: OrganizationDBO)

    @Delete
    suspend fun removeAll(organizations: List<OrganizationDBO>)

    @Delete
    suspend fun removeOrganization(organization: OrganizationDBO)

    @Query("DELETE FROM organizations")
    suspend fun clean()
}