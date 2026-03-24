package com.example.memeapp.model

data class MemeListResponse(
    val count: Int,
    val memes: List<MemeRedditResponse>
)