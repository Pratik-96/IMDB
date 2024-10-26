package com.example.imdbclone

sealed class Screens(val route:String) {
    object HomeScreen:Screens("HomeScreen")
    object DetailScreen:Screens("DetailScreen")
}