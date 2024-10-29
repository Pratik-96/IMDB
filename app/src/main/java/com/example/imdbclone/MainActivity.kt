package com.example.imdbclone

import android.icu.text.CaseMap.Title
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.imdbclone.ui.theme.IMDBCloneTheme


data class NavigationItem(

    val name: String,
    val url: String,
    val unSelectedItem: ImageVector? = null,
    val selectedIcon: ImageVector? = null
)

val items = listOf(
    NavigationItem(
        name = "Home",
        url = "",
        unSelectedItem = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    ),
    NavigationItem(
        name = "Netflix",
        url = "https://media.movieofthenight.com/services/netflix/logo-dark-theme.svg"
    ),
    NavigationItem(
        name = "Prime Video",
        url = "https://media.movieofthenight.com/services/prime/logo-dark-theme.svg"
    ),
    NavigationItem(
        name = "Hotstar",
        url = "https://media.movieofthenight.com/services/disneyhotstar/logo-dark-theme.svg"
    ),
    NavigationItem(
        name = "Apple Tv",
        url = "https://media.movieofthenight.com/services/apple/logo-dark-theme.svg"
    ),
    NavigationItem(
        name = "Sony Liv",
        url = "https://media.movieofthenight.com/services/sonyliv/logo-dark-theme.png"
    ),
    NavigationItem(
        name = "Zee5",
        url = "https://media.movieofthenight.com/services/zee5/logo-dark-theme.svg"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMDBCloneTheme {


                NavDrawer()


//                val navController = rememberNavController()
//                IMDBApp(navController)

            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavDrawer() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ModalNavigationDrawer(drawerContent = {}, drawerState = drawerState, modifier = Modifier.fillMaxSize().background(color = Color.Black)) {
            Scaffold(topBar = {
                ImportantText("STREAM-MATE")
            }) {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IMDBCloneTheme {
        NavDrawer()
    }
}