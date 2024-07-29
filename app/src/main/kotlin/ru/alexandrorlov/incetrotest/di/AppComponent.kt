package ru.alexandrorlov.incetrotest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.alexandrorlov.incetrotest.common.di.FavoriteModule
import ru.alexandrorlov.incetrotest.common.domain.repository.FavoriteRepository
import ru.alexandrorlov.incetrotest.data.local.dao.OrganizationsDao
import ru.alexandrorlov.incetrotest.data.local.di.DatabaseModule
import ru.alexandrorlov.incetrotest.data.network.di.NetworkModule
import ru.alexandrorlov.incetrotest.feature.data.source.api.DetailApi
import ru.alexandrorlov.incetrotest.feature.data.source.api.MainApi
import ru.alexandrorlov.incetrotest.feature.di.FeatureModule
import ru.alexandrorlov.incetrotest.feature.di.dependecies.CommonDependencies
import ru.alexandrorlov.incetrotest.feature.di.dependecies.DetailDependencies
import ru.alexandrorlov.incetrotest.feature.di.dependecies.MainDependencies

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        FavoriteModule::class,
        FeatureModule::class,
    ]
)
@AppScope
interface AppComponent : MainDependencies, DetailDependencies, CommonDependencies {

    override fun mainApi(): MainApi

    override fun detailApi(): DetailApi

    override fun organizationsDao(): OrganizationsDao

    override fun favoriteRepository(): FavoriteRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
        ): AppComponent
    }
}