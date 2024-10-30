package com.example.imdbclone

import android.util.Log
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

    private val _netflixMovieState = mutableStateOf(ShowState())
    val netflixMovieState: State<ShowState> = _netflixMovieState

    private val _appleShowState = mutableStateOf(ShowState())
    val appleShowState: State<ShowState> = _appleShowState


    private val _appleMovieState = mutableStateOf(ShowState())
    val appleMovieState: State<ShowState> = _appleMovieState

    private val _primeShowState = mutableStateOf(ShowState())
    val primeShowDetails:State<ShowState> = _primeShowState

    private val _primeMovieState = mutableStateOf(ShowState())
    val primeMovieDetails:State<ShowState> = _primeMovieState

    private val _hotstarShowState = mutableStateOf(ShowState())
    val hotstarShowDetails:State<ShowState> = _hotstarShowState

    init {
        fetchNetflixShows()
        fetchNetflixMovies()
        fetchAppleShows()
        fetchAppleMovies()
        fetchPrimeShows()
        fetchPrimeMovies()
        fetchHotstarShows()
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


    private fun fetchHotstarShows() {
        viewModelScope.launch {
            try {

                val response = imdbService.getFilteredShows("in","hotstar","hotstar","series",75)
                _hotstarShowState.value = _hotstarShowState.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            }catch (e:Exception){
                _hotstarShowState.value = _hotstarShowState.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

private fun fetchNetflixMovies() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","netflix","movie")
                _netflixMovieState.value = _netflixMovieState.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _netflixMovieState.value = _netflixMovieState.value.copy(
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

private fun fetchAppleMovies() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","apple","movie")
                _appleMovieState.value = _appleMovieState.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _appleMovieState.value = _appleMovieState.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

private fun fetchPrimeShows() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","prime","series")
                _primeShowState.value = _primeShowState.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _primeShowState.value = _primeShowState.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

private fun fetchPrimeMovies() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","prime","movie")
                _primeMovieState.value = _primeMovieState.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _primeMovieState.value = _primeMovieState.value.copy(
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

