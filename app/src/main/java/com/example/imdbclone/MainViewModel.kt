package com.example.imdbclone

import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.DataClasses.ShowDetails
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _showState = mutableStateOf(ShowState())
    val showState: State<ShowState> = _showState

    init {
        fetchShows()
    }

    private fun fetchShows() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","netflix","series")
                _showState.value = _showState.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            }catch (e:Exception){
                _showState.value = _showState.value.copy(
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

