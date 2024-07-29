package ru.alexandrorlov.incetrotest.data.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.alexandrorlov.incetrotest.common.data.source.api.FavoriteApi
import ru.alexandrorlov.incetrotest.data.network.api.AuthHeaderInterceptor
import ru.alexandrorlov.incetrotest.data.network.api.Constant
import ru.alexandrorlov.incetrotest.di.AppScope
import ru.alexandrorlov.incetrotest.feature.data.source.api.DetailApi
import ru.alexandrorlov.incetrotest.feature.data.source.api.MainApi

@Module
class NetworkModule {

    @[Provides AppScope]
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create()

    @[Provides AppScope]
    fun provideDetailApi(retrofit: Retrofit): DetailApi = retrofit.create()

    @[Provides AppScope]
    fun provideFavoriteApi(retrofit: Retrofit): FavoriteApi = retrofit.create()

    @[Provides AppScope]
    fun provideRetrofit(
        factory: Converter.Factory,
        httpClient: OkHttpClient,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(factory)
            .client(httpClient)
            .build()

    @Provides
    fun getConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthHeaderInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}