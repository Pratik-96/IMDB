package com.example.imdbclone

sealed class Screens(val route:String) {
    object HomeScreen:Screens("HomeScreen")
    object SearchScreen:Screens("SearchScreen")
    object DetailScreen:Screens("DetailScreen")
    object NetflixScreen:Screens("NetflixScreen")
    object PrimeScreen:Screens("PrimeScreen")
    object AppleScreen:Screens("AppleScreen")
    object HotstarScreen:Screens("HotstarScreen")
    object SonyScreen:Screens("SonyScreen")
    object ZeeScreen:Screens("ZeeScreen")
}