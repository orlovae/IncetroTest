package ru.alexandrorlov.incetrotest.main.di

import retrofit2.Retrofit
import ru.alexandrorlov.incetrotest.data.local.AppDatabase

interface MainDependencies {
    fun retrofit(): Retrofit

    fun db(): AppDatabase
}