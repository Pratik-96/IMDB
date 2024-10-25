package com.example.imdbclone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun IMDBApp(navController: NavController){
    val showViewModel:MainViewModel = viewModel()
    TopShowScreen(showViewModel)
}