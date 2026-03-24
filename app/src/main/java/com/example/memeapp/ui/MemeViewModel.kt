package com.example.memeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memeapp.network.MemeApi
import kotlinx.coroutines.launch
import com.example.memeapp.model.MemeRedditResponse

sealed interface MemeUiState {
    object Loading : MemeUiState
    data class Success(val url: String, val title: String) : MemeUiState
    object Error : MemeUiState
}

class MemeViewModel : ViewModel() {

    private var memeList: List<MemeRedditResponse> = emptyList()
    private var currentIndex = 0
    var memeUiState: MemeUiState = MemeUiState.Loading
        private set

    init {
        getMeme()
    }
    fun getMeme() {
        viewModelScope.launch {

            memeUiState = MemeUiState.Loading

            try {
                if (currentIndex >= memeList.size) {

                    val response = MemeApi.retrofitService.getMemes(10)
                    memeList = response.memes.shuffled()
                    currentIndex = 0
                }

                val meme = memeList[currentIndex]
                currentIndex++

                memeUiState = MemeUiState.Success(
                    meme.url,
                    meme.title
                )

            } catch (e: Exception) {
                memeUiState = MemeUiState.Error
            }
        }
    }

}