package ru.alexandrorlov.incetrotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.alexandrorlov.incetrotest.data.local.converters.Converters
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.models.OrganizationsDBO

@Database(entities = [OrganizationsDBO::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun organizationDao(): OrganizationsDao

    companion object {
        const val DB_NAME = "incetro.db"
    }

}
