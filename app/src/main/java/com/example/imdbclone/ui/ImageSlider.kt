package com.example.imdbclone.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imdbclone.DataClasses.ShowDetails
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(data: List<ShowDetails>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) { }
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


        val img = data[page].imageSet.horizontalPoster?.w720
        Box(modifier = Modifier.wrapContentSize()) {
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
                                    Color.Black.copy(alpha = 0.3f),
                                    Color.Black
                                ),
                                startY = 0f, // Start at the top
                                endY = 100f
                            )
                    )
                    .align(Alignment.BottomCenter)
            ){

//               TODO:Add genre
                val name = data[page].title
                val genreDetailList = data[page].genres
                val genreList:MutableList<String> = mutableListOf()
                for (i in 0 until genreDetailList.size){
                    genreDetailList.get(i)?.name?.let { genreList.add(it) }
                }

                val context = LocalContext.current
                val genres = genreList.joinToString(" • ") { it }

                Toast.makeText(context,"${name}: ${genres}",Toast.LENGTH_SHORT).show()

            }
        }
    }
}


