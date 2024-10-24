package com.example.imdbclone

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey:String):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("x-rapidapi-key",apiKey)
            .build()
        return chain.proceed(request)
    }
}