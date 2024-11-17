package com.example.imdbclone.Screen

sealed class Screens(val route:String) {
    object HomeScreen: Screens("HomeScreen")
    object SearchScreen: Screens("SearchScreen")
    object MarvelScreen: Screens("MarvelScreen")
    object DetailScreen: Screens("DetailScreen")
    object NetflixScreen: Screens("NetflixScreen")
    object PrimeScreen: Screens("PrimeScreen")
    object AppleScreen: Screens("AppleScreen")
    object HotstarScreen: Screens("HotstarScreen")
    object SignUp: Screens("SignUp")
    object Login: Screens("Login")

}