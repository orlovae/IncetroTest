package ru.alexandrorlov.incetrotest.detail.di

import dagger.Component
import ru.alexandrorlov.incetrotest.di.MultiViewModelFactory

@Component(
    dependencies = [DetailDependencies::class],
    modules = [DetailModule::class]
)
@DetailScope
interface DetailComponent {

    val factory: MultiViewModelFactory

//    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface DetailComponentFactory {
        fun create(dependencies: DetailDependencies): DetailComponent
    }
}