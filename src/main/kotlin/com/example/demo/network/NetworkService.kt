package com.example.demo.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private const val READ_TIMEOUT_SEC = 30L
    private const val CONNECT_TIMEOUT_SEC = 10L
    private const val BASE_URL = "https://dog.ceo/"

    private val httpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()

    val apiRestService: ApiRestService = retrofit.create(ApiRestService::class.java)
}