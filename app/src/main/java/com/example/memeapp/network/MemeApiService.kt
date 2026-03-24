package com.example.memeapp.network

import com.example.memeapp.model.MemeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApiService {

    @GET("gimme/{count}")
    suspend fun getMemes(
        @Path("count") count: Int
    ): MemeListResponse
}