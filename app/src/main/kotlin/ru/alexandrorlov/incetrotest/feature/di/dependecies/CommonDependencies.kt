package ru.alexandrorlov.incetrotest.feature.di.dependecies

import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao

interface CommonDependencies {

    fun organizationsDao(): OrganizationsDao

    fun favoriteRepository(): FavoriteRepository
}