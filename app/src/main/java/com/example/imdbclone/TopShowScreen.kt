package com.example.imdbclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.imdbclone.DataClasses.ShowDetails

@Composable
fun TopShowScreen(viewModel: MainViewModel) {

    val netflixState by viewModel.netflixShowState
    val appleState by viewModel.appleShowState

    Column(modifier = Modifier.fillMaxSize()) {
        StateScreen(title = "Top 10 Netflix Shows",netflixState)
        StateScreen(title = "Top 10 Apple Shows",appleState)
    }
}

@Composable
fun StateScreen(title:String,showState:MainViewModel.ShowState){

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = title, fontSize = 16.sp , modifier = Modifier.padding(8.dp))

        when{

            showState.loading->{
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }

            showState.error != null->{
                Text(showState.error)
            }
            else->{
                ShowsScreen(showState.list)
            }
        }
    }

}


@Composable
fun ShowsScreen(shows: List<ShowDetails>) {

    LazyHorizontalGrid(
        modifier = Modifier.wrapContentSize(),
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(shows) { item ->

            ShowItem(item)

        }
    }

}


@Composable
fun ShowItem(item: ShowDetails) {

    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(
                painter = rememberAsyncImagePainter(item.imageSet.verticalPoster?.w720),
                modifier = Modifier.size(width = 100.dp, height = 150.dp),
                contentDescription = item.title
            )
    }


}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {


}


