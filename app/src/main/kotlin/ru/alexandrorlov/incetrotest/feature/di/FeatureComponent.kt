package ru.alexandrorlov.incetrotest.feature.di

import dagger.Component
import ru.alexandrorlov.incetrotest.common.di.ViewModelFactory
import ru.alexandrorlov.incetrotest.feature.di.dependecies.CommonDependencies
import ru.alexandrorlov.incetrotest.feature.di.dependecies.DetailDependencies
import ru.alexandrorlov.incetrotest.feature.di.dependecies.MainDependencies

@Component(
    dependencies = [
        MainDependencies::class,
        DetailDependencies::class,
        CommonDependencies::class,
    ],
    modules = [FeatureModule::class]
)
@FeatureScope
interface FeatureComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface FeatureComponentFactory {
        fun create(
            mainDependencies: MainDependencies,
            detailDependencies: DetailDependencies,
            commonDependencies: CommonDependencies,
            ): FeatureComponent
    }
}