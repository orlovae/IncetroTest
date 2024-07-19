package ru.alexandrorlov.incetrotest.data.network.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alexandrorlov.incetrotest.data.network.api.AuthHeaderInterceptor
import ru.alexandrorlov.incetrotest.data.network.api.Constant

@Module
class NetworkModule {

    @Provides
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