package com.example.imdbclone.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.DataClasses.ShowResponse
import com.example.imdbclone.imdbService
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

class HotstarViewModel:ViewModel() {
    private val _topShows = mutableStateOf(MainViewModel.ShowState())
    val topShows: State<MainViewModel.ShowState> = _topShows

    private val _actionMovies = mutableStateOf(MainViewModel.ShowState())
    val actionMovies: State<MainViewModel.ShowState> = _actionMovies


    private val _scifiMovies = mutableStateOf(MainViewModel.ShowState())
    val scifiMovies: State<MainViewModel.ShowState> = _scifiMovies


    private val _marvelShows = mutableStateOf(MainViewModel.SearchShowState())
    val marvelShows: State<MainViewModel.SearchShowState> = _marvelShows



    fun fetchShowId(id:String) {
        viewModelScope.launch {
            try {

                val response = imdbService.searchShow(id)
                _marvelShows.value = _marvelShows.value.copy(
                    error = null,
                    item = response,
                    loading = false
                )

            }catch (e:Exception){
                _marvelShows.value = _marvelShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }




    fun fetchScifiMovies(
        country: String,
        service: String,
        catalogs: String,
        showType: String,
        ratingMin: Int,
        genre: String,
        language: String
    ) {
        viewModelScope.launch {
            try {

                val response = imdbService.getFilteredShows(
                    country,
                    service,
                    catalogs,
                    showType,
                    ratingMin,
                    genre,
                    language
                )
                _scifiMovies.value = _scifiMovies.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            } catch (e: Exception) {
                _scifiMovies.value = _scifiMovies.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

    fun fetchActionMovies(
        country: String,
        service: String,
        catalogs: String,
        showType: String,
        ratingMin: Int,
        genre: String,
        language: String
    ) {
        viewModelScope.launch {
            try {

                val response = imdbService.getFilteredShows(
                    country,
                    service,
                    catalogs,
                    showType,
                    ratingMin,
                    genre,
                    language
                )
                _actionMovies.value = _actionMovies.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            } catch (e: Exception) {
                _actionMovies.value = _actionMovies.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }
    private var count = mutableStateOf(0)

        fun fetchTopShows(country: String, service: String, catalogs: String, ratingMin: Int) {
            viewModelScope.launch {
                count.value++
                Log.d("count", "slider: Called "+count)
                try {

                    val response = imdbService.getTopShows(country, service, catalogs, ratingMin)
                    _topShows.value = _topShows.value.copy(
                        error = null,
                        list = response.shows,
                        loading = false
                    )

                } catch (e: Exception) {
                    _topShows.value = _topShows.value.copy(
                        error = e.message,
                        loading = false
                    )
                }
            }
        }
    }
