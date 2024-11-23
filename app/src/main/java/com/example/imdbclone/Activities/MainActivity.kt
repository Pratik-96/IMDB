package com.example.imdbclone.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
import com.example.imdbclone.DataClasses.SavedShowDetails
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.Screen.AppleScreen
import com.example.imdbclone.Screen.DetailScreen
import com.example.imdbclone.Screen.HotstarScreen
import com.example.imdbclone.Screen.ImportantText
import com.example.imdbclone.Screen.LargeText
import com.example.imdbclone.Screen.ListScreen
import com.example.imdbclone.Screen.NetflixScreen
import com.example.imdbclone.Screen.PrimeScreen
import com.example.imdbclone.Screen.Screen
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.Screen.TopShowScreen
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.util.ArrayList


private lateinit var auth:FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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


        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid?:"null"
        val database = FirebaseDatabase.getInstance().reference
        val context = LocalContext.current as Activity



        var dataFetched by remember { mutableStateOf(false) }

        var showList = mutableListOf<SavedShowDetails>()


        database.child(uid).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<SavedShowDetails>()
                for(child in snapshot.children){
                    val showData = child.getValue(SavedShowDetails::class.java)
                    showData?.let { list.add(it) }
                }
                showList = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })










        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
        var selectedItem by rememberSaveable { mutableStateOf("") }

        // Navigation
        val showViewModel: MainViewModel = viewModel()
        val navHostController = rememberNavController()

        auth = FirebaseAuth.getInstance()
        val userName = auth.currentUser?.displayName?:"User"




        ModalNavigationDrawer(
            drawerContent = {

                ModalDrawerSheet(
                    drawerContainerColor = Color.Black,
                    drawerContentColor = Color.Gray,
                ) {
                    Text(
                        "Hey ${userName}..!!",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 24.dp, top = 16.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    showViewModel.items.forEachIndexed { index, navigationItem ->
                        NavigationDrawerItem(
                            label = {
                                if (index == 0 || index==1 || index==6)  {
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
                                                modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
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
                                        Text(
                                            text = navigationItem.name,
                                            color = Color.White,
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(16.dp),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index

                                // Navigation

                                selectedItem = navigationItem.url


                                scope.launch {


                                    when (index) {
                                        0 -> navHostController.navigate(Screens.HomeScreen.route)
                                        1 -> navHostController.navigate(Screens.MyListScreen.route)
                                        2 -> navHostController.navigate(Screens.NetflixScreen.route)
                                        3 -> navHostController.navigate(Screens.PrimeScreen.route)
                                        4 -> navHostController.navigate(Screens.HotstarScreen.route)
                                        5 -> navHostController.navigate(Screens.AppleScreen.route)
                                        6 ->{
                                            auth.signOut()
                                            context.startActivity(Intent(context,Authentication::class.java))
                                            context.finish()
                                        }
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
                .background(Color.White),
                topBar = {

                    TopAppBar(
                        title = {


                            Row(
                                modifier = Modifier.wrapContentSize(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (selectedItem.isNotEmpty()) {
                                    NavLogo(selectedItem)
//                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search", modifier = Modifier.padding(8.dp))
                                }
                                if (selectedItemIndex == 0) {
                                    LargeText("For $userName")
                                }
                                if (selectedItemIndex == 1){
                                    ImportantText("My List")
                                }
                                Spacer(Modifier.weight(1f))
                                IconButton(onClick = {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            SearchActivity::class.java
                                        )
                                    )

                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search"
                                    )
                                }

                            }


                        },
                        navigationIcon = {

                            IconButton(onClick = {

                                scope.launch {
                                    drawerState.open()
                                }

                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = null
                                )

                            }
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color.DarkGray),
                        colors = TopAppBarColors(
                            containerColor = Color.Transparent,
                            scrolledContainerColor = Color.DarkGray,
                            navigationIconContentColor = Color.White,
                            titleContentColor = Color.White,
                            actionIconContentColor = Color.White
                        )
                    )

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
                            DetailScreen(showData, navHostController)
                        }
                    }
                    composable(route = Screens.NetflixScreen.route) {


                        NetflixScreen(navHostController, navigateToDetail = {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "ShowData",
                                it
                            )
                            navHostController.navigate(Screens.DetailScreen.route)
                        })
                    }

                    composable(route = Screens.MyListScreen.route) {
                        ListScreen()
                    }


                    composable(route = Screens.PrimeScreen.route) {
                        PrimeScreen(navigateToDetail = {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "ShowData",
                                it
                            )
                            navHostController.navigate(Screens.DetailScreen.route)
                        })
                    }
                    composable(route = Screens.HotstarScreen.route) {
                        HotstarScreen(navigateToDetail = {
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "ShowData",
                                it
                            )
                            navHostController.navigate(Screens.DetailScreen.route)
                        }, navigateToMarvel = {
                            navHostController.navigate(Screens.MarvelScreen.route)

                        }
                        )
                    }
                    composable(route = Screens.MarvelScreen.route) {
                        Screen({
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "ShowData",
                                it
                            )
                            navHostController.navigate(Screens.DetailScreen.route)
                        })
                    }
                    composable(route = Screens.AppleScreen.route) {
                        AppleScreen({
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                "ShowData",
                                it
                            )
                            navHostController.navigate(Screens.DetailScreen.route)
                        })
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
            .size(width = 70.dp, height = 52.dp),
        contentDescription = null,

        )
}
