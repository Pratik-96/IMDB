package com.example.imdbclone

import android.icu.text.CaseMap.Title
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import kotlinx.coroutines.launch
import org.w3c.dom.Text


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
        ModalNavigationDrawer(
            drawerContent = {

                ModalDrawerSheet(
                    drawerContainerColor = Color.Black,
                    drawerContentColor = Color.Gray
                ) {

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
                                scope.launch {

                                    drawerState.close()
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .background(Color.Black),
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Black, // Set black for unselected items
                                selectedContainerColor = Color.DarkGray // Set dark gray for selected item
                            ))

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
                .background(Color.Black), topBar = {
                TopAppBar(title = { Text("STREAM-MATE") }, navigationIcon = {
                    IconButton(onClick = {

                        scope.launch {
                            drawerState.open()
                        }

                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                })
            }) {}

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