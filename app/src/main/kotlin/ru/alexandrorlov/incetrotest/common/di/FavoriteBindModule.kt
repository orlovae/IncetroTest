package ru.alexandrorlov.incetrotest.common.di

import dagger.Binds
import dagger.Module
import ru.alexandrorlov.incetrotest.common.data.repository.FavoriteRepositoryImpl
import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository

@Module
interface FavoriteBindModule {

    @[Binds]
    fun bindFavoriteRepositoryImplToFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl,
    ): FavoriteRepository
}