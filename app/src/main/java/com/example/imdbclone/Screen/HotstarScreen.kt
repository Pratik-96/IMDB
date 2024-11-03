package com.example.imdbclone.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.imdbclone.ui.theme.IMDBCloneTheme

@Composable
fun HotstarScreen() {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Black), horizontalAlignment = Alignment.CenterHorizontally) {




    }
}


@Preview
@Composable
private fun HotPrev() {
    IMDBCloneTheme {
        HotstarScreen()
    }
}