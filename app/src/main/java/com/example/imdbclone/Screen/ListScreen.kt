package com.example.imdbclone.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ListScreen(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("ListScreen")
    }
}