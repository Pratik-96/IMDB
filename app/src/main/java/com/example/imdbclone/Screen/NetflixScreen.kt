package com.example.imdbclone.Screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.imdbclone.Activities.SearchStateScreen
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ViewModels.NetflixViewModel

@Composable
fun NetflixScreen(navHostController: NavHostController, navigateToDetail: (ShowDetails) -> Unit) {
    val netflixViewModel: NetflixViewModel = viewModel()
    val mainViewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}

    netflixViewModel.fetchShowId("6")
    val state = netflixViewModel.fetchShow.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        when {
            state.loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadingLogo(mainViewModel.items.get(1).url, Color.Black,Color.Red)
                }

            }

            state.error != null -> {
                    ImportantText("Error: ${state.error}")
            }

            else -> {

                NetflixContent(navigateToDetail,state)

            }
        }

    }
}

@Composable
fun NetflixContent(navigateToDetail: (ShowDetails) -> Unit, state: MainViewModel.SearchShowState) {
    val netflixViewModel: NetflixViewModel = viewModel()
    val mainViewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            Box(
                modifier = Modifier
                    .padding(16.dp)

                    .fillMaxWidth(), contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = rememberAsyncImagePainter(state.item?.imageSet?.verticalPoster?.w720),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(450.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                )


                val genresList: List<String> = listOf("Drama", "Fantasy", "Horror")
                val genres = genresList.joinToString(" • ") { it }
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .background(
                            brush =
                            Brush
                                .verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.4f),
                                        Color.Black.copy(alpha = 0.6f)
                                    ),
                                    startY = 0f, // Start at the top
                                    endY = 100f
                                )
                        )
                        .shadow(elevation = 0.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            genres,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                                .align(Alignment.BottomCenter),

                            )
                    }
                    Box(modifier = Modifier.wrapContentSize()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {


                                    launcher.launch(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://www.netflix.com/watch/80057281")
                                        )
                                    )


                                }, modifier = Modifier
                                    .height(55.dp)
                                    .padding(8.dp),
//                            .shadow(shape = RoundedCornerShape(4.dp), elevation = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.Black,
                                    containerColor = Color.White
                                ), shape = RoundedCornerShape(4.dp)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PlayArrow,
                                    contentDescription = "Play"
                                )
                                ButtonText("PLAY")

                            }
                            Button(
                                onClick = {

                                    //TODO:Store to watchlist
                                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT)
                                        .show()

                                }, modifier = Modifier
                                    .height(55.dp)
                                    .padding(8.dp),
//                            .shadow(shape = RoundedCornerShape(4.dp), elevation = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = Color.Black.copy(0.7f)
                                ), shape = RoundedCornerShape(4.dp)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "add"
                                )
                                Text(
                                    text = "My List",
                                    color = Color.White,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                    }
                }


            }

        }

//
        netflixViewModel.fetchFilteredShows("in", "netflix", "netflix", "series", 85, "","")
        val topShowState = netflixViewModel.topShows.value
        item {
            SearchStateScreen(
                topShowState, navigateToDetail, false, "Critically Acclaimed TV Shows",
            )
        }
//
//
//            netflixViewModel.fetchDramaShows("in", "netflix", "netflix", "series", 75, "thriller")
//            val dramaState = netflixViewModel.topDramas.value
//            item {
//                SearchStateScreen(
//                    dramaState, navigateToDetail, false, "Thrillers",
//                )
//            }
//

    }

}
