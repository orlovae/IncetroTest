package ru.alexandrorlov.incetrotest.feature.di.dependecies

import ru.alexandrorlov.incetrotest.feature.data.source.api.DetailApi

interface DetailDependencies {

    fun detailApi(): DetailApi

}