package com.example.imdbclone.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.imdbclone.Activities.SearchStateScreen
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.R
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ui.ImageSlider
import com.example.imdbclone.ui.Studios
import com.example.imdbclone.ui.theme.HotstarBackground
import com.example.imdbclone.ui.theme.IMDBCloneTheme

@Composable
fun HotstarScreen(navigateToDetail: (ShowDetails) -> Unit, navigateToMarvel: () -> Unit) {

    val viewModel: HotstarViewModel = viewModel()
    val mainViewModel:MainViewModel= viewModel()
    viewModel.fetchTopShows("in", "hotstar", "hotstar", 80)
//    viewModel.fetchActionMovies("in", "hotstar", "hotstar", "movie", 70, "action","")
    mainViewModel.fetchFilteredShows(mainViewModel._genreShowState[mainViewModel.hotstarIndex],"in","hotstar","hotstar","movie",75,"action","")
    val actionMoviesState = mainViewModel.genreShowState[mainViewModel.hotstarIndex].value

    viewModel.fetchScifiMovies("in", "hotstar", "hotstar", "movie", 70, "scifi","")
    val scifiMovieState = viewModel.scifiMovies.value

    val showState = viewModel.topShows
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        when {

            showState.value.loading -> {
                Column {
                    LoadingLogo(mainViewModel.items.get(4).url, HotstarBackground, Color.White)

                }
            }


            showState.value.error != null -> {
                Text(showState.value.error.toString(), color = Color.White)
            }

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .background(HotstarBackground)
                        .fillMaxSize()
                ) {
                    item { ImageSlider(showState.value.list, navigateToDetail) }

                    item { Studios(navigateToMarvel) }

                    item {
                        SearchStateScreen(
                            actionMoviesState, navigateToDetail, false, "Action Movies",
                        )
                    }

//                    item {
//                        SearchStateScreen(
//                            scifiMovieState, navigateToDetail, false, "Science Fiction Movies",
//                        )
//                    }
//

                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Bg() {
    IMDBCloneTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.img1),
                    null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .shadow(elevation = 0.dp)
                        .background(
                            brush =
                            Brush
                                .verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,

                                        HotstarBackground.copy(1f)
                                    ),
                                    startY = 0f, // Start at the top
                                    endY = 100f
                                )
                        )
                        .align(Alignment.BottomCenter)
                        .zIndex(3f)
                ) {


                }
            }
        }

    }
}

