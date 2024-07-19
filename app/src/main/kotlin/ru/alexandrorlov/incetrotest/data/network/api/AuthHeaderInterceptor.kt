package ru.alexandrorlov.incetrotest.data.network.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.alexandrorlov.incetrotest.data.network.api.Constant.HEADER_AUTH
import ru.alexandrorlov.incetrotest.data.network.api.Constant.HEADER_TOKEN_KEY
import ru.alexandrorlov.incetrotest.data.network.api.Constant.HEADER_TOKEN_VALUE
import javax.inject.Inject

class AuthHeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val header: String = getHeader()

        val requestWithHeader: Request = chain.request()
            .newBuilder()
            .addHeader(HEADER_AUTH, header)
            .build()

        return chain.proceed(requestWithHeader)
    }

    private fun getHeader(): String =
        HEADER_TOKEN_KEY + HEADER_TOKEN_VALUE
}