package com.example.memeapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MemeApi {
    private const val BASE_URL = "https://meme-api.com/"

    val retrofitService: MemeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemeApiService::class.java)
    }
}