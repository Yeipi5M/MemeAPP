package com.example.memeapp.ui

import com.example.memeapp.model.MemeRedditResponse

sealed class MemeState {

    object Loading : MemeState()

    data class Success(
        val memes: List<MemeRedditResponse>
    ) : MemeState()

    object Error : MemeState()
}