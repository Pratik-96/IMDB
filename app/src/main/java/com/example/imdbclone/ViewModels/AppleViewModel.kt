package com.example.imdbclone.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.imdbService
import kotlinx.coroutines.launch

class AppleViewModel: ViewModel() {

    private val _fetchShow = mutableStateOf(MainViewModel.SearchShowState())
    val fetchShow: State<MainViewModel.SearchShowState> = _fetchShow

    private val _fetchTopShow = mutableStateOf(MainViewModel.ShowState())
    val fetchTopShow: State<MainViewModel.ShowState> = _fetchTopShow

    private val _fetchTopMovie = mutableStateOf(MainViewModel.ShowState())
    val fetchTopMovie: State<MainViewModel.ShowState> = _fetchTopMovie


     fun fetchAppleShows() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","apple","series")
                _fetchTopShow.value = _fetchTopShow.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _fetchTopShow.value = _fetchTopShow.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

  fun fetchAppleMovies() {
        viewModelScope.launch {
            try {

                val response = imdbService.getShows("in","apple","movie")
                _fetchTopMovie.value = _fetchTopMovie.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _fetchTopMovie.value = _fetchTopMovie.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }


    fun fetchShowId(id:String) {
        viewModelScope.launch {
            try {

                val response = imdbService.searchShow(id)
                _fetchShow.value = _fetchShow.value.copy(
                    error = null,
                    item = response,
                    loading = false
                )

            }catch (e:Exception){
                _fetchShow.value = _fetchShow.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }


}
