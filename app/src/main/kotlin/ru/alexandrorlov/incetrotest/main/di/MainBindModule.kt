package ru.alexandrorlov.incetrotest.main.di

import dagger.Binds
import dagger.Module
import ru.alexandrorlov.incetrotest.data.repository.MainRepositoryImpl
import ru.alexandrorlov.incetrotest.main.domain.repository.MainRepository

@Module
interface MainBindModule {

    @Binds
    fun bindMainRepositoryImplToMainRepository(
        mainRepositoryImpl: MainRepositoryImpl,
    ): MainRepository
}