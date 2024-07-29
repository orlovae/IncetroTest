package ru.alexandrorlov.incetrotest.feature.di.bindmodule

import dagger.Binds
import dagger.Module
import ru.alexandrorlov.incetrotest.feature.data.repository.DetailRepositoryImpl
import ru.alexandrorlov.incetrotest.feature.domain.repository.DetailRepository

@Module
interface DetailBindModule {

    @Binds
    fun bindDetailRepositoryImplToDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl,
    ): DetailRepository
}