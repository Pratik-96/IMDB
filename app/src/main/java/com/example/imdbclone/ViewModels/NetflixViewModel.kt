package com.example.imdbclone.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.ViewModels.MainViewModel.ShowState
import com.example.imdbclone.imdbService
import kotlinx.coroutines.launch

class NetflixViewModel:ViewModel() {


    private val _topShows = mutableStateOf(ShowState())
    val topShows: State<ShowState> = _topShows

    private val _topDramas = mutableStateOf(ShowState())
    val topDramas: State<ShowState> = _topDramas

    private val _fetchShow = mutableStateOf(MainViewModel.SearchShowState())
    val fetchShow: State<MainViewModel.SearchShowState> = _fetchShow


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

    fun fetchFilteredShows(country:String,service:String,catalogs:String,showType:String,ratingMin:Int,genre:String){
        viewModelScope.launch {
            try {

                val response = imdbService.getFilteredShows(country,service,catalogs,showType,ratingMin,genre)
                _topShows.value = _topShows.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            }catch (e:Exception){
                _topShows.value = _topShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

    fun fetchDramaShows(country:String,service:String,catalogs:String,showType:String,ratingMin:Int,genre:String){
        viewModelScope.launch {
            try {

                val response = imdbService.getFilteredShows(country,service,catalogs,showType,ratingMin,genre)
                _topDramas.value = _topDramas.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            }catch (e:Exception){
                _topDramas.value = _topDramas.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }
}

