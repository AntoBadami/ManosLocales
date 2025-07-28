package com.tecmov2025.manoslocales.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    //private const val BASE_URL = "https://manos-locales.free.beeceptor.com"
    //private const val BASE_URL = "https://manoslocalesapp.app.smartmock.io"
    private const val BASE_URL = "https://manoslocales.app.smartmock.io"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
