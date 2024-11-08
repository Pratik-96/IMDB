package com.example.imdbclone.ui

import android.content.Intent
import android.net.Uri
import android.widget.ImageButton
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.imdbclone.Activities.SearchStateScreen
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.R
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ui.theme.DeepGray
import com.example.imdbclone.ui.theme.HotstarBackground
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(data: List<ShowDetails>, navigateToDetail: (ShowDetails) -> Unit) {

    val viewModel: HotstarViewModel = viewModel()
//    viewModel.fetchActionMovies("in", "hotstar", "hotstar", "movie", 70, "action")
    val actionMoviesState = viewModel.actionMovies.value
//    viewModel.fetchScifiMovies("in", "hotstar", "hotstar", "movie", 70, "scifi")
    val scifiMovieState = viewModel.scifiMovies.value
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {


        val pagerState = rememberPagerState(initialPage = 0)
        LaunchedEffect(Unit) {
            while (true) {
                delay(5000)
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % data.size)
            }
        }



        com.google.accompanist.pager.HorizontalPager(
            count = data.size,
            state = pagerState,
            itemSpacing = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            Column {
                val img = data[page].imageSet.horizontalPoster?.w720
                Box(modifier = Modifier
                    .wrapContentSize()
                    .height(250.dp)) {

                    AsyncImage(
                        img,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
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
//                                            HotstarBackground.copy(alpha = 0.5f),
                                            HotstarBackground
                                        ),
                                        startY = 0f, // Start at the top
                                        endY = 50f
                                    )
                            )
                            .align(Alignment.BottomCenter)
                            .zIndex(3f)
                    ) {


                    }



                }
                Box(modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .background(HotstarBackground)
                    .align(Alignment.CenterHorizontally)) {
                    val genreDetailList = data[page].genres
                    val genreList: MutableList<String> = mutableListOf()
                    for (i in 0 until genreDetailList.size) {
                        genreDetailList.get(i)?.name?.let { genreList.add(it) }
                    }

                    val genres = genreList.joinToString(" • ") { it }


                    Text(
                        text = genres,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(0.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
                val launcher =
                    rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}
                Box(modifier = Modifier
                    .wrapContentSize()
                    .background(HotstarBackground)
                    .align(Alignment.CenterHorizontally)) {

                    var link = data[page].streamingOptions?.`in`?.get(0)?.link.toString()
                    for (i in 0 until data[page].streamingOptions?.`in`?.size!!) {
                        if (data[page].streamingOptions?.`in`?.get(i)?.service?.id.equals("hotstar")) {
                            link = data[page].streamingOptions?.`in`?.get(i)?.link.toString()
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.Center
                    ) {


                        Box(modifier = Modifier.wrapContentSize()) {
                            Button(
                                onClick = {

                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(link)
                                    )
                                    launcher.launch(intent)

                                }, modifier = Modifier
                                    .height(55.dp)
                                    .padding(8.dp),
//                            .shadow(shape = RoundedCornerShape(4.dp), elevation = 10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = DeepGray
                                ), shape = RoundedCornerShape(8.dp)

                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PlayArrow,
                                    contentDescription = "play"
                                )
                                Text(
                                    text = "Watch Now",
                                    color = Color.White,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }


                        Box(modifier = Modifier.wrapContentSize()) {
                            val context = LocalContext.current
                            Button(
                                onClick = {


                                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT)
                                        .show()
                                    //TODO: Add to watchlist


                                }, modifier = Modifier
                                    .wrapContentSize()
                                    .padding(4.dp)
                                    .align(Alignment.Center),
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White,
                                    containerColor = DeepGray
                                ), shape = RoundedCornerShape(8.dp)
                            ) {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                            }
                        }


                    }

                }


            }

        }


    }
}

@Composable
fun StudioLogos(logo:String) {
    AsyncImage(logo, contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.height(70.dp).width(110.dp).clip(RoundedCornerShape(4.dp)))

}

@Composable
fun Studios() {

    val disneyLogo = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/3776/1443776-h-993a8447aed1"
    val pixarLogo = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/3793/1443793-h-7aacf32a2124"
    val marvelLogo = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/3782/1443782-h-afdfe6e7c6cb"
    val starWarsLogo = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/3794/1443794-h-96534e1745fa"
    val natGeoLogo = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/3790/1443790-h-f4c6cb8892d1"
    val hotstarLogo = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/894/1726575090894-h"

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(4.dp)){
                StudioLogos(disneyLogo)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(4.dp)){
                StudioLogos(pixarLogo)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(4.dp)){
               StudioLogos(marvelLogo)
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(4.dp)){
                StudioLogos(starWarsLogo)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(4.dp)){
               StudioLogos(natGeoLogo)
            }
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(4.dp)){
               StudioLogos(hotstarLogo)
            }
        }

    }
}


