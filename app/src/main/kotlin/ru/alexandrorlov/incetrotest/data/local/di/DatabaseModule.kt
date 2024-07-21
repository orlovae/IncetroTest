package ru.alexandrorlov.incetrotest.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.alexandrorlov.incetrotest.data.local.AppDatabase
import ru.alexandrorlov.incetrotest.data.local.AppDatabase.Companion.DB_NAME
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.di.AppScope

@Module
class DatabaseModule {

    @[Provides AppScope]
    fun localSource(database: AppDatabase): OrganizationsDao =
        database.organizationDao()


    @[Provides AppScope]
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
}
