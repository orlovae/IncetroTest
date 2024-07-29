package ru.alexandrorlov.incetrotest.feature.di.bindmodule

import dagger.Binds
import dagger.Module
import ru.alexandrorlov.incetrotest.feature.data.repository.MainRepositoryImpl
import ru.alexandrorlov.incetrotest.feature.domain.repository.MainRepository

@Module
interface MainBindModule {

    @Binds
    fun bindMainRepositoryImplToMainRepository(
        mainRepositoryImpl: MainRepositoryImpl,
    ): MainRepository
}