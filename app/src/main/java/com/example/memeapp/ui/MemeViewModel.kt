package com.example.memeapp.ui

import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.example.memeapp.model.MemeRedditResponse
import com.example.memeapp.network.MemeApi
import kotlinx.coroutines.launch

class MemeViewModel : ViewModel() {
    private val _state = MutableLiveData<MemeState>()
    val state: LiveData<MemeState> = _state
    private val _memeList = MutableLiveData<List<MemeRedditResponse>>()
    val memeList: LiveData<List<MemeRedditResponse>> = _memeList


    fun getMemeList() {
        _state.value = MemeState.Loading

        viewModelScope.launch {
            try {
                val response = MemeApi.retrofitService.getMemes(10)
                _state.value = MemeState.Success(response.memes)
            } catch (e: Exception) {
                _state.value = MemeState.Error
            }
        }
    }
}