package com.example.imdbclone.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.imdbclone.Activities.SearchStateScreen
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ui.theme.IMDBCloneTheme

@Composable
fun NetflixScreen() {

    val netflixViewModel: MainViewModel = viewModel()
    val netflixFilteredState by netflixViewModel.filteredShows
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter("https://cdn.movieofthenight.com/show/6/poster/vertical/en/720.jpg?Expires=1749503848&Signature=bG~7EFZYfvih1oJwuAlmg09ohwlbOlaac2hs-dovb0YkhRavEvnyeAQPLrvlBmXRcNoLzYuYKuahyTCrhmBj-oz~ZyzEwB2chx8cbEZBus97BFdjEQBxjvJL4eo3u~-QcLEjVqGnbln4tAd8gCA1ZbHpy44HMmkNWebA~5IHR5PzPd~YGZClJyx2x~heuVSufPznErCY~gxwLU9HvcjYQaMNQHCHgoGUwcVdDfcIKKYRPUX-KyZKc9kibx1Cnpw7YmFYX3jpW0dmPhEDKsYe08nrpXMEQ7hy-A~fJFeD-zSk6LDJ0ZVJXeJsTA-7D6jWvGzPoQS0q-imLd6IquuJ3A__&Key-Pair-Id=KK4HN3OO4AT5R"),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(500.dp)
                            .clip(shape = RoundedCornerShape(16.dp))

                    )
                }
            }
            netflixViewModel.fetchFilteredShows("in","netflix","netflix","series",85)

                item { SearchStateScreen(netflixFilteredState,{},false,"Critically Acclaimed TV Shows") }

        }


    }
}


@Preview
@Composable
private fun NetflixPrev() {
    IMDBCloneTheme {
        NetflixScreen()
    }
}