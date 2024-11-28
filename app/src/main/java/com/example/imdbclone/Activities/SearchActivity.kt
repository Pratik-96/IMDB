package com.example.imdbclone.Activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.Screen.DetailScreen
import com.example.imdbclone.Screen.NormalText
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ui.theme.DeepGray
import kotlinx.coroutines.delay

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = DeepGray)
                        .padding(innerPadding)
                ) {
                    val navController: NavHostController = rememberNavController()
                    val showViewModel: MainViewModel = viewModel()
                    showViewModel.searchShows.value.loading = false
                    NavHost(
                        navController = navController, startDestination = Screens.SearchScreen.route
                    ) {
                        composable(route = Screens.SearchScreen.route) {
                            SearchScreen(showViewModel, navigateToDetail = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "ShowData", it
                                )
                                navController.navigate(Screens.DetailScreen.route)
                            })

                        }
                        composable(route = Screens.DetailScreen.route) {
                            val showData =
                                navController.previousBackStackEntry?.savedStateHandle?.get<ShowDetails>(
                                    "ShowData"
                                )
                            if (showData != null) {
                                DetailScreen(showData, navController)
                            }
                        }
                    }
                }


            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(showViewModel: MainViewModel, navigateToDetail: (ShowDetails) -> Unit) {

    val context = LocalContext.current as Activity
    var title by rememberSaveable{ mutableStateOf("") }
    var isSearching by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier = Modifier.wrapContentSize()) {

                IconButton(
                    onClick = {
                        context.finish()
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.DarkGray.copy(
                            alpha = 0.6f
                        )
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

            }

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {


                TextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }, keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        autoCorrect = true,
                        imeAction = ImeAction.Search
                    ), keyboardActions = KeyboardActions(onSearch = {
                        showViewModel.searchShow(title.lowercase())
                        showViewModel.searchShows.value.loading = true
                        isSearching = true

                    }), textStyle = TextStyle(
                        fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold
                    ), colors = TextFieldDefaults.colors().copy(
                        focusedTextColor = Color.White,
//                        containerColor = Color.DarkGray,
                        focusedContainerColor = Color.DarkGray.copy(
                            alpha = 0.6f
                        ),
                        unfocusedContainerColor = Color.DarkGray.copy(
                            alpha = 0.6f
                        ),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(40.dp)), value = title, onValueChange = {
                        title = it
                    }, singleLine = true
                )
            }
        }

        if (isSearching){
            Text(
                text = "Showing results for \"${title}\"",
                color = Color.White,
                modifier = Modifier.padding(8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                fontFamily = sarabunFont
            )
        }


        val searchViewModel by showViewModel.searchShows


        SearchStateScreen(searchViewModel, navigateToDetail, true, "")

    }

}


@Composable
fun SearchStateScreen(
    showState: MainViewModel.ShowState,
    navigateToDetail: (ShowDetails) -> Unit,
    vertical: Boolean,
    header: String
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when {

            showState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }

            showState.error != null -> {
                Text(showState.error, color = Color.White)
            }

            else -> {
                Log.d("TAG", "SearchStateScreen: " + showState.list)
                SearchShowsScreen(showState.list, navigateToDetail, vertical, header)
            }
        }
    }

}


@Composable
fun SearchShowsScreen(
    shows: List<ShowDetails>,
    navigateToDetail: (ShowDetails) -> Unit,
    vertical: Boolean,
    header: String
) {
    val context = LocalContext.current


    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context).components {
        add(SvgDecoder.Factory())  // Add SVG decoder explicitly
    }.build()
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    ) {

        if (vertical) {

            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                columns = GridCells.Fixed(1),
            ) {
                items(shows) { item ->

                        SearchShowItem(item, navigateToDetail)


                }
            }
        } else {


            Column {
                Text(
                    text = header,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.padding(4.dp))
                LazyHorizontalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    rows = GridCells.Fixed(1)
                ) {
                    items(shows) { item ->


                        NetflixShowItem(item, navigateToDetail)

                    }
                }

            }
        }


    }
}

@Composable
fun NetflixShowItem(item: ShowDetails, navigateToDetail: (ShowDetails) -> Unit) {
    val context = LocalContext.current


    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context).components {
        add(SvgDecoder.Factory())  // Add SVG decoder explicitly
    }.memoryCachePolicy(CachePolicy.ENABLED).diskCachePolicy(CachePolicy.ENABLED).build()

    Column(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                try {

                    navigateToDetail(item)
                } catch (e: Exception) {
                    Log.d("nav", "ShowItem: " + e.message)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val painter = rememberAsyncImagePainter(
            model = item.imageSet.verticalPoster?.w360 // Adjust as needed
            , imageLoader = imageLoader
        )


        AsyncImage(
            item.imageSet.verticalPoster?.w360,
            modifier = Modifier
                .size(width = 130.dp, height = 200.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
        )
    }


}


@Composable
fun SearchShowItem(item: ShowDetails, navigateToDetail: (ShowDetails) -> Unit) {


    val context = LocalContext.current


    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context).components {
        add(SvgDecoder.Factory())  // Add SVG decoder explicitly
    }.memoryCachePolicy(CachePolicy.ENABLED).diskCachePolicy(CachePolicy.ENABLED).build()

    Column(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                try {

                    navigateToDetail(item)
                } catch (e: Exception) {
                    Log.d("nav", "ShowItem: " + e.message)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val painter = rememberAsyncImagePainter(
            model = item.imageSet.verticalPoster?.w360 // Adjust as needed
            , imageLoader = imageLoader
        )

        Column(
            modifier = Modifier
//                .height(160.dp)
                .background(Color.Black)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 110.dp, height = 150.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Column {
                    Text(
                        item.title,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                        fontFamily = sarabunFont
                    )
                    NormalText(item.overview)


                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.8.dp)
                    .background(Color.DarkGray)
                    .padding(start = 8.dp, end = 8.dp)
            )

        }
    }
}





