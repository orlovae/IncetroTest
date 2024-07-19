package ru.alexandrorlov.incetrotest.main.di

import dagger.Component
import ru.alexandrorlov.incetrotest.MainActivity
import ru.alexandrorlov.incetrotest.di.MultiViewModelFactory

@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class]
)
interface MainComponent {

    val factory: MultiViewModelFactory

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface MainComponentFactory {
        fun create(dependencies: MainDependencies): MainComponent
    }
}