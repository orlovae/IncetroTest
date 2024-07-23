package ru.alexandrorlov.incetrotest.detail.di

import dagger.Component
import ru.alexandrorlov.incetrotest.di.MultiViewModelFactory
import ru.alexandrorlov.incetrotest.ui.theme.MainActivity

@Component(
    dependencies = [DetailDependencies::class],
    modules = [DetailModule::class]
)
@DetailScope
interface DetailComponent {

    val factory: MultiViewModelFactory

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface DetailComponentFactory {
        fun create(dependencies: DetailDependencies): DetailComponent
    }
}