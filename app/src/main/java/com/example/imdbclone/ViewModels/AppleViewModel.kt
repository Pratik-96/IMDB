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
