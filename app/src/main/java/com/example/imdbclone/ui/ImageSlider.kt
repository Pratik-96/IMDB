package com.example.imdbclone.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.R
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay



@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(data:List<ShowDetails>) {

    Column(modifier = Modifier.fillMaxSize()) {  }
    val pagerState = rememberPagerState(initialPage = 0)
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % data.size)
        }
    }



    com.google.accompanist.pager.HorizontalPager(
        count = data.size,
        state = pagerState,
        itemSpacing = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) { page->
        Box(modifier = Modifier.fillMaxWidth()) {
            val img = data[page].imageSet.horizontalPoster?.w720
            AsyncImage(
                img,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
    }
}

