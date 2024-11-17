package com.example.imdbclone.ViewModels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.imdbService
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


    private val _searchShows = mutableStateOf(ShowState())
    val searchShows:State<ShowState> = _searchShows


    init {
//        fetchNetflixShows()
//        fetchNetflixMovies()
//        fetchAppleShows()
//        fetchAppleMovies()
//        fetchPrimeShows()
//        fetchPrimeMovies()
//        fetchHotstarShows()
    }




     fun searchShow(title:String) {
        viewModelScope.launch {
            try {

                val response = imdbService.searchShow("in", title = title)
                _searchShows.value = _searchShows.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            }catch (e:Exception){
                _searchShows.value = _searchShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
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

                val response = imdbService.getFilteredShows("in","hotstar","hotstar","series",75,"","")
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


    data class NavigationItem(

        val name: String,
        val url: String,
        val unSelectedItem: ImageVector? = null,
        val selectedIcon: ImageVector? = null,
        val route: String
    )

    val items = listOf(
        NavigationItem(
            name = "Home",
            url = "",
            unSelectedItem = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            route = Screens.HomeScreen.route

        ),NavigationItem(
            name = "My List",
            url = "",
            unSelectedItem = Icons.Outlined.List,
            selectedIcon = Icons.Filled.List,
            route = Screens.MyListScreen.route

        ), NavigationItem(
            name = "Netflix",
            url = "https://media.movieofthenight.com/services/netflix/logo-dark-theme.svg",
            route = Screens.NetflixScreen.route
        ), NavigationItem(
            name = "Prime Video",
            url = "https://media.movieofthenight.com/services/prime/logo-dark-theme.svg",
            route = Screens.PrimeScreen.route

        ), NavigationItem(
            name = "Hotstar",
            url = "https://media.movieofthenight.com/services/disneyhotstar/logo-dark-theme.svg",
            route = Screens.HotstarScreen.route
        ), NavigationItem(
            name = "Apple TV",
            url = "https://media.movieofthenight.com/services/apple/logo-dark-theme.svg",
            route = Screens.AppleScreen.route
        ),NavigationItem(
            name = "Logout",
            url = "",
            unSelectedItem = Icons.Outlined.Logout,
            selectedIcon = Icons.Filled.Logout,
            route = Screens.AppleScreen.route
        )
    )



    data class ShowState(
        val error: String? = null,
        val list: List<ShowDetails> = emptyList(),
        var loading: Boolean = true
    )
    data class SearchShowState(
        val error: String? = null,
        val item: ShowDetails? = null ,
        var loading: Boolean = true
    )
}

