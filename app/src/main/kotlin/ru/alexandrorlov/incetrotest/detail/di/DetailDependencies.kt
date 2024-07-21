package ru.alexandrorlov.incetrotest.detail.di

import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.detail.data.source.api.DetailApi

interface DetailDependencies {
    fun detailApi(): DetailApi

    fun organizationsDao(): OrganizationsDao

    fun favoriteRepository(): FavoriteRepository
}
