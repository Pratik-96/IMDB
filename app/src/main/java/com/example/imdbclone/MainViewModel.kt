package com.example.imdbclone

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.DataClasses.ShowDetails
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _netflixShowState = mutableStateOf(ShowState())
    val netflixShowState: State<ShowState> = _netflixShowState

    private val _appleShowState = mutableStateOf(ShowState())
    val appleShowState: State<ShowState> = _appleShowState

    init {
        fetchNetflixShows()
        fetchAppleShows()
    }

    private fun fetchNetflixShows() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","netflix","series")
                _netflixShowState.value = _netflixShowState.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _netflixShowState.value = _netflixShowState.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

    private fun fetchAppleShows() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","apple","series")
                _appleShowState.value = _appleShowState.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _appleShowState.value = _appleShowState.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }


    data class ShowState(
        val error: String? = null,
        val list: List<ShowDetails> = emptyList(),
        val loading: Boolean = true
    )
}

