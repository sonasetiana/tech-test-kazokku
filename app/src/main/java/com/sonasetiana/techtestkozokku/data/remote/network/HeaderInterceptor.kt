package com.sonasetiana.techtestkozokku.data.remote.network

import com.sonasetiana.techtestkozokku.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("app-id", BuildConfig.APP_ID)
            .build()
        return chain.proceed(request)
    }
}