package com.example.imdbclone.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.imdbService
import kotlinx.coroutines.launch

class PrimeViewModel : ViewModel() {

    private val _topPrimeShows = mutableStateOf(MainViewModel.ShowState())
    val topPrimeShows: State<MainViewModel.ShowState> = _topPrimeShows


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

