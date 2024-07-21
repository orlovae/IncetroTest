package ru.alexandrorlov.incetrotest.detail.di

import dagger.Binds
import dagger.Module
import ru.alexandrorlov.incetrotest.detail.data.DetailRepositoryImpl
import ru.alexandrorlov.incetrotest.detail.domain.repository.DetailRepository

@Module
interface DetailBindModule {

    @Binds
    fun bindDetailRepositoryImplToDetailRepository(
        detailRepositoryImpl: DetailRepositoryImpl,
    ): DetailRepository
}