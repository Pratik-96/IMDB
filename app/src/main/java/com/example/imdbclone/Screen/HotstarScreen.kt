package com.example.imdbclone.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ui.ImageSlider
import com.example.imdbclone.ui.theme.IMDBCloneTheme

@Composable
fun HotstarScreen() {

    val viewModel:HotstarViewModel = viewModel()
    viewModel.fetchTopShows("in","hotstar","hotstar",80)
    val showState = viewModel.topShows
    Box(modifier = Modifier.fillMaxSize()){

        when {

            showState.value.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }

            showState.value.error != null -> {
                Text(showState.value.error.toString(), color = Color.White)
            }

            else -> {
                ImageSlider(showState.value.list)
            }
        }
    }

}


@Preview
@Composable
private fun HotPrev() {
    IMDBCloneTheme {
        HotstarScreen()
    }
}