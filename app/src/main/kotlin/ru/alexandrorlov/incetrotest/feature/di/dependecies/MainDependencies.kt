package ru.alexandrorlov.incetrotest.feature.di.dependecies

import ru.alexandrorlov.incetrotest.feature.data.source.api.MainApi

interface MainDependencies {

    fun mainApi(): MainApi

}