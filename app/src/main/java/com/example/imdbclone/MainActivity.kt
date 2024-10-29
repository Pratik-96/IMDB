package com.example.imdbclone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.Screen.AppleScreen
import com.example.imdbclone.Screen.HotstarScreen
import com.example.imdbclone.Screen.NetflixScreen
import com.example.imdbclone.Screen.PrimeScreen
import com.example.imdbclone.Screen.SonyScreen
import com.example.imdbclone.Screen.ZeeScreen
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import kotlinx.coroutines.launch


data class NavigationItem(

    val name: String,
    val url: String,
    val unSelectedItem: ImageVector? = null,
    val selectedIcon: ImageVector? = null,
    val route: String
)

val items = listOf(
    NavigationItem(
        name = "Home",
        url = "",
        unSelectedItem = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = Screens.HomeScreen.route

    ), NavigationItem(
        name = "Netflix",
        url = "https://media.movieofthenight.com/services/netflix/logo-dark-theme.svg",
        route = Screens.NetflixScreen.route
    ), NavigationItem(
        name = "Prime Video",
        url = "https://media.movieofthenight.com/services/prime/logo-dark-theme.svg",
        route = Screens.PrimeScreen.route

    ), NavigationItem(
        name = "Hotstar",
        url = "https://media.movieofthenight.com/services/disneyhotstar/logo-dark-theme.svg",
        route = Screens.HotstarScreen.route
    ), NavigationItem(
        name = "Apple Tv",
        url = "https://media.movieofthenight.com/services/apple/logo-dark-theme.svg",
        route = Screens.AppleScreen.route
    ), NavigationItem(
        name = "Sony Liv",
        url = "https://media.movieofthenight.com/services/sonyliv/logo-dark-theme.png",
        route = Screens.SonyScreen.route
    ), NavigationItem(
        name = "Zee5",
        url = "https://media.movieofthenight.com/services/zee5/logo-dark-theme.svg",
        route = Screens.ZeeScreen.route
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer() {
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.Black
    ) {


        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }


        // Navigation
        val showViewModel: MainViewModel = viewModel()
        val navHostController = rememberNavController()





        ModalNavigationDrawer(
            drawerContent = {

                ModalDrawerSheet(
                    drawerContainerColor = Color.Black,
                    drawerContentColor = Color.Gray,
                ) {
                    Text(
                        "Hey Pratik..!!",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    items.forEachIndexed { index, navigationItem ->
                        NavigationDrawerItem(
                            label = {
                                if (index == 0) {
                                    (if (index == selectedItemIndex) {
                                        navigationItem.selectedIcon
                                    } else navigationItem.unSelectedItem)?.let {
                                        Row(
                                            modifier = Modifier.wrapContentSize(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                imageVector = it,
                                                contentDescription = null,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                            ImportantText(navigationItem.name)
                                        }
                                    }

                                } else {
                                    Row(
                                        modifier = Modifier.wrapContentSize(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        NavLogo(navigationItem.url)
                                        ImportantText(navigationItem.name)
                                    }
                                }
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index

                                // Navigation




                                scope.launch {


                                    when (index) {
                                        0 -> navHostController.navigate(Screens.HomeScreen.route)
                                        1 -> navHostController.navigate(Screens.NetflixScreen.route)
                                        2 -> navHostController.navigate(Screens.PrimeScreen.route)
                                        3 -> navHostController.navigate(Screens.HotstarScreen.route)
                                        4 -> navHostController.navigate(Screens.AppleScreen.route)
                                        5 -> navHostController.navigate(Screens.SonyScreen.route)
                                        6 -> navHostController.navigate(Screens.ZeeScreen.route)
                                    }
                                    drawerState.close()

                                }
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .background(Color.Black),
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Black, // Set black for unselected items
                                selectedContainerColor = Color.DarkGray // Set dark gray for selected item
                            )
                        )

                    }
                }

            },
            drawerState = drawerState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Scaffold(modifier = Modifier
                .fillMaxSize()
                .background(Color.White), topBar = {
                TopAppBar(title = {
                        Text("STREAM-MATE")

                }, navigationIcon = {
                    IconButton(onClick = {

                        scope.launch {
                            drawerState.open()
                        }

                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                })
            }) { innerPadding ->
                val showViewModel: MainViewModel = viewModel()

                NavHost(
                    navController = navHostController,
                    startDestination = Screens.HomeScreen.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(route = Screens.HomeScreen.route) {
                        TopShowScreen(showViewModel, navigateToDetail = {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "ShowData",
                                it
                            )
                            navHostController.navigate(Screens.DetailScreen.route)
                        })

                    }

                    composable(route = Screens.DetailScreen.route) {
                        val showData =
                            navHostController.previousBackStackEntry?.savedStateHandle?.get<ShowDetails>(
                                "ShowData"
                            )
                        if (showData != null) {
                            DetailScreen(showData)
                        }
                    }
                    composable(route = Screens.NetflixScreen.route) {
                        NetflixScreen()
                    }

//
                    composable(route = Screens.PrimeScreen.route) {
                        PrimeScreen()
                    }
                    composable(route = Screens.HotstarScreen.route) {
                        HotstarScreen()
                    }
                    composable(route = Screens.AppleScreen.route) {
                        AppleScreen()
                    }
                    composable(route = Screens.SonyScreen.route) {
                        SonyScreen()
                    }
                    composable(route = Screens.ZeeScreen.route) {
                        ZeeScreen()
                    }

                }


            }

        }
    }
}


@Composable
fun NavLogo(url: String) {
    val context = LocalContext.current
    // Redundant code make a fun

    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context).components {
        add(SvgDecoder.Factory())  // Add SVG decoder explicitly
    }.build()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(url).size(Size.ORIGINAL)  // Adjust as needed
            .build(), imageLoader = imageLoader
    )
    Image(
        painter = painter,
        modifier = Modifier
            .padding(top = 8.dp)
            .size(width = 50.dp, height = 32.dp),
        contentDescription = null,

        )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IMDBCloneTheme {
        NavDrawer()
    }
}