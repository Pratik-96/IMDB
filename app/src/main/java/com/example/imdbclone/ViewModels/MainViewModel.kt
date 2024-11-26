package com.example.imdbclone.ViewModels

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.DataClasses.SavedShowDetails
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.imdbService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

private lateinit var auth: FirebaseAuth

class MainViewModel : ViewModel() {

    val actionIndex = 0
    val siFiIndex = 1
    val adventureIndex = 2
    val animationIndex = 3
    val comedyIndex = 4
    val crimeIndex = 5
    val documentaryIndex = 6
    val dramaIndex = 7
    val fantasyIndex = 8
    val horrorIndex = 9
    val romanceIndex = 10
    val thrillerIndex = 11
    val hotstarIndex = 12
    val topGenreShowsIndex = 13



    private val _netflixShowState = mutableStateOf(ShowState())
    val netflixShowState: State<ShowState> = _netflixShowState

    private val _netflixMovieState = mutableStateOf(ShowState())
    val netflixMovieState: State<ShowState> = _netflixMovieState

    private val _appleShowState = mutableStateOf(ShowState())
    val appleShowState: State<ShowState> = _appleShowState


    private val _appleMovieState = mutableStateOf(ShowState())
    val appleMovieState: State<ShowState> = _appleMovieState

    private val _primeShowState = mutableStateOf(ShowState())
    val primeShowDetails: State<ShowState> = _primeShowState

    private val _primeMovieState = mutableStateOf(ShowState())
    val primeMovieDetails: State<ShowState> = _primeMovieState

    private val _hotstarShowState = mutableStateOf(ShowState())
    val hotstarShowDetails: State<ShowState> = _hotstarShowState


    private val _searchShows = mutableStateOf(ShowState())
    val searchShows: State<ShowState> = _searchShows

    private val _fetchList = mutableStateOf(ListState())
    val fetchList: State<ListState> = _fetchList


    private val _actionShows = mutableStateOf(ShowState())
    val actionShows: State<ShowState> = _actionShows

    private val _siFiShows = mutableStateOf(ShowState())
    val siFiShows: State<ShowState> = _siFiShows

    private val _adventureShows = mutableStateOf(ShowState())
    val adventureShows: State<ShowState> = _adventureShows

    private val _animationShows = mutableStateOf(ShowState())
    val animationShows: State<ShowState> = _animationShows

    private val _comedyShows = mutableStateOf(ShowState())
    val comedyShows: State<ShowState> = _comedyShows

    private val _crimeShows = mutableStateOf(ShowState())
    val crimeShows: State<ShowState> = _crimeShows

    private val _documentaryShows = mutableStateOf(ShowState())
    val documentaryShows: State<ShowState> = _documentaryShows


    private val _dramaShows = mutableStateOf(ShowState())
    val dramaShows: State<ShowState> = _dramaShows

    private val _fantasyShows = mutableStateOf(ShowState())
    val fantasyShows: State<ShowState> = _fantasyShows

    private val _horrorShows = mutableStateOf(ShowState())
    val horrorShows: State<ShowState> = _horrorShows

    private val _romanceShows = mutableStateOf(ShowState())
    val romanceShows: State<ShowState> = _romanceShows

    private val _thrillerShows = mutableStateOf(ShowState())
    val thrillerShows: State<ShowState> = _thrillerShows

    private val _topGenreShows = mutableStateOf(ShowState())
    val topGenreShows: State<ShowState> = _topGenreShows

    var selectedGenres = ""
    var fetched = false

    private val _genreState = mutableStateOf(GenreState())
    val genreState:State<GenreState> = _genreState


    val genreShowState: MutableList<State<ShowState>> = mutableListOf(
        actionShows,
        siFiShows,
        adventureShows,
        animationShows,
        comedyShows,
        crimeShows,
        documentaryShows,
        dramaShows,
        fantasyShows,
        horrorShows,
        romanceShows,
        thrillerShows,
        hotstarShowDetails,
        topGenreShows
    )

    //TODO: fetch genre data from firebase.

     val _genreShowState: MutableList<MutableState<ShowState>> = mutableListOf(
        _actionShows,
        _siFiShows,
        _adventureShows,
        _animationShows,
        _comedyShows,
        _crimeShows,
        _documentaryShows,
        _dramaShows,
        _fantasyShows,
        _horrorShows,
        _romanceShows,
        _thrillerShows,
        _hotstarShowState,
         _topGenreShows
    )

    private val _showStates: MutableList<MutableState<ShowState>> = mutableListOf(
        _netflixShowState,
        _netflixMovieState,
        _appleShowState,
        _appleMovieState,
        _primeShowState,
        _primeMovieState
    )

    val showStates: MutableList<State<ShowState>> = mutableListOf(
        netflixShowState,
        netflixMovieState,
        appleShowState,
        appleMovieState,
        primeShowDetails,
        primeMovieDetails
    )


    init {
        fetchDataFromFirebase()
//        dynamicDataFetching()
    }



    fun fetchFilteredShows(
        state: MutableState<ShowState>,
        country: String = "in",
        service: String,
        catalogs: String,
        showType: String,
        ratingMin: Int,
        genres: String,
        language: String,
    ) {
        viewModelScope.launch {
            try {

                val response =
                    imdbService.getFilteredShows(
                        country,
                        service,
                        catalogs,
                        showType,
                        ratingMin,
                        genres,
                        language
                    )
                state.value = state.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            } catch (e: Exception) {
                state.value = state.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }


    private fun dynamicDataFetching() {
        fetchData(_showStates[0], "netflix", "in", "series")
        fetchData(_showStates[1], "netflix", "in", "movie")
        fetchData(_showStates[2], "apple", "in", "series")
        fetchData(_showStates[3], "apple", "in", "movie")
        fetchData(_showStates[3], "prime", "in", "series")
        fetchData(_showStates[3], "prime", "in", "movie")
    }

    private fun fetchData(
        state: MutableState<ShowState>,
        service: String,
        country: String = "in",
        showType: String
    ) {
        viewModelScope.launch {
            try {
                val response = imdbService.getShows(country, service, showType)
                state.value = state.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            } catch (e: Exception) {
                _netflixShowState.value = _netflixShowState.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }


    fun fetchDataFromFirebase() {

        viewModelScope.launch {
            auth = FirebaseAuth.getInstance()
            val uid = auth.currentUser?.uid ?: "null"
            val database = FirebaseDatabase.getInstance().reference

            database.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<SavedShowDetails>()
                    for (child in snapshot.children) {
                        val showData = child.getValue(SavedShowDetails::class.java)
                        showData?.let { list.add(it) }
                    }
                    _fetchList.value = _fetchList.value.copy(
                        list = list,
                        loading = false
                    )

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })

            database.child("user_data").child(uid).child("selectedGenres")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        val genres =
                            snapshot.getValue(object : GenericTypeIndicator<List<String>>() {})
                        selectedGenres = genres?.shuffled()?.take(2)?.joinToString(",").toString()
                        fetched = true
                        _genreState.value = _genreState.value.copy(
                            genres = selectedGenres,
                            loading = false
                        )
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }


    fun searchShow(title: String) {
        viewModelScope.launch {
            try {

                val response = imdbService.searchShow("in", title = title)
                _searchShows.value = _searchShows.value.copy(
                    error = null,
                    list = response,
                    loading = false
                )

            } catch (e: Exception) {
                _searchShows.value = _searchShows.value.copy(
                    error = e.message,
                    loading = false
                )
            }
        }
    }

    private fun fetchHotstarShows() {
        viewModelScope.launch {
            try {

                val response =
                    imdbService.getFilteredShows("in", "hotstar", "hotstar", "series", 75, "", "")
                _hotstarShowState.value = _hotstarShowState.value.copy(
                    error = null,
                    list = response.shows,
                    loading = false
                )

            } catch (e: Exception) {
                _hotstarShowState.value = _hotstarShowState.value.copy(
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

        ), NavigationItem(
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
        ), NavigationItem(
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

    data class ListState(
        var list: List<SavedShowDetails> = emptyList(),
        var loading: Boolean = true
    )

    data class GenreState(
        var genres:String = "emptyList()",
        var loading: Boolean = true
    )

    data class SearchShowState(
        val error: String? = null,
        val item: ShowDetails? = null,
        var loading: Boolean = true
    )
}

