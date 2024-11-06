package com.example.imdbclone.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.DataClasses.ShowResponse
import com.example.imdbclone.imdbService
import kotlinx.coroutines.launch

class HotstarViewModel:ViewModel() {
    private val _topShows =  mutableStateOf(MainViewModel.ShowState())
    val topShows:State<MainViewModel.ShowState> = _topShows

    fun fetchTopShows(country:String,service:String,catalogs:String,ratingMin:Int){
        viewModelScope.launch {
            try {

                val response = imdbService.getTopShows(country,service,catalogs,ratingMin)
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
}