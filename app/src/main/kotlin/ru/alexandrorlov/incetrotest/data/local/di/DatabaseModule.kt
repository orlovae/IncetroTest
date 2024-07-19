package ru.alexandrorlov.incetrotest.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.alexandrorlov.incetrotest.data.local.AppDatabase
import ru.alexandrorlov.incetrotest.data.local.AppDatabase.Companion.DB_NAME

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
