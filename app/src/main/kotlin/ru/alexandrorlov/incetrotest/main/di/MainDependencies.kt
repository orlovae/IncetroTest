package ru.alexandrorlov.incetrotest.main.di

import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.main.data.source.api.MainApi

interface MainDependencies {
    fun mainApi(): MainApi

    fun organizationsDao(): OrganizationsDao

    fun favoriteRepository(): FavoriteRepository
}