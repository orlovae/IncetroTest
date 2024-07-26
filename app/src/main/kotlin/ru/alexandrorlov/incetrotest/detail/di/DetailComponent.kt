package ru.alexandrorlov.incetrotest.detail.di

import dagger.Component
import ru.alexandrorlov.incetrotest.common.di.ViewModelFactory

@Component(
    dependencies = [DetailDependencies::class],
    modules = [DetailModule::class]
)
@DetailScope
interface DetailComponent {

    fun getViewModelFactory(): ViewModelFactory


    @Component.Factory
    interface DetailComponentFactory {
        fun create(dependencies: DetailDependencies): DetailComponent
    }
}