package com.example.imdbclone.ViewModels

import android.media.tv.TvContract.Programs.Genres
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.imdbService
import kotlinx.coroutines.launch

class PrimeViewModel : ViewModel() {

    private val _topPrimeShows = mutableStateOf(MainViewModel.ShowState())
    val topPrimeShows: State<MainViewModel.ShowState> = _topPrimeShows

    private val _hindiMovies = mutableStateOf(MainViewModel.ShowState())
    val hindiMovies: State<MainViewModel.ShowState> = _hindiMovies

    private val _hindiShows = mutableStateOf(MainViewModel.ShowState())
    val hindiShows: State<MainViewModel.ShowState> = _hindiShows

    fun fetchHindiMovies(country:String,service: String,catalogs: String,showType: String,ratingMin: Int,genres: String,language:String){
        viewModelScope.launch {
            try {
                val response = imdbService.getFilteredShows(country,service,catalogs,showType,ratingMin,genres,language)
                _hindiMovies.value = _hindiMovies.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )
            }catch (e:Exception){
                _hindiMovies.value = _hindiMovies.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

    fun fetchHindiSeries(country:String,service: String,catalogs: String,showType: String,ratingMin: Int,genres: String,language:String){
        viewModelScope.launch {
            try {
                val response = imdbService.getFilteredShows(country,service,catalogs,showType,ratingMin,genres,language)
                _hindiShows.value = _hindiShows.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )
            }catch (e:Exception){
                _hindiShows.value = _hindiShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }


    fun fetchTopShows(country: String, service: String, catalogs: String, ratingMin: Int) {
        viewModelScope.launch {
            try {

                val response = imdbService.getTopShows(country, service, catalogs, ratingMin)
                _topPrimeShows.value = _topPrimeShows.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            } catch (e: Exception) {
                _topPrimeShows.value = _topPrimeShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

    fun fetchTopPrimeShows(country: String, service: String, showType: String) {
        viewModelScope.launch {
            try {

                val response = imdbService.getAllShows(
                    country,
                    service
                )
                _topPrimeShows.value = _topPrimeShows.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            } catch (e: Exception) {
                _topPrimeShows.value = _topPrimeShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }
}

