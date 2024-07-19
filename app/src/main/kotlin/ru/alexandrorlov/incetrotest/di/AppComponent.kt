package ru.alexandrorlov.incetrotest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import ru.alexandrorlov.incetrotest.data.local.AppDatabase
import ru.alexandrorlov.incetrotest.data.local.di.DatabaseModule
import ru.alexandrorlov.incetrotest.data.network.di.NetworkModule
import ru.alexandrorlov.incetrotest.main.di.MainDependencies

@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
    ]
)
interface AppComponent : MainDependencies {

    override fun retrofit(): Retrofit

    override fun db(): AppDatabase

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            context: Context,
        ): AppComponent
    }
}