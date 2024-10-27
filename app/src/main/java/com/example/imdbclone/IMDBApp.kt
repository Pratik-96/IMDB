package com.example.imdbclone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.imdbclone.DataClasses.ShowDetails

@Composable
fun IMDBApp(navHostController: NavHostController){
    val showViewModel:MainViewModel = viewModel()

    NavHost(navController = navHostController, startDestination = Screens.HomeScreen.route){
        composable(route=Screens.HomeScreen.route) {
            TopShowScreen(showViewModel, navigateToDetail = {
                navHostController.currentBackStackEntry?.savedStateHandle?.set("ShowData",it)
                navHostController.navigate(Screens.DetailScreen.route)
            })

        }
        composable(route = Screens.DetailScreen.route) {
            val showData = navHostController.previousBackStackEntry?.
            savedStateHandle?.get<ShowDetails>("ShowData")
            if (showData != null) {
                DetailScreen(showData)
            }
        }
    }



}