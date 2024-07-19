package ru.alexandrorlov.incetrotest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO

@Dao
interface OrganizationsDao {

    @Query("SELECT * FROM organizations")
    fun getAll(): Flow<List<OrganizationsDBO>>

    @Query("SELECT * FROM organizations WHERE organizations.isFavorite = 1")
    fun getAllFavorite(): Flow<List<OrganizationsDBO>>

    @Query("SELECT * FROM organizations WHERE organizations.id = :id")
    fun getOrganizationById(id: Long): OrganizationsDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(organizations: List<OrganizationsDBO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrganization(organization: OrganizationsDBO)

    @Delete
    suspend fun removeAll(organizations: List<OrganizationsDBO>)

    @Delete
    suspend fun removeOrganization(organization: OrganizationsDBO)

    @Query("DELETE FROM organizations")
    suspend fun clean()
}