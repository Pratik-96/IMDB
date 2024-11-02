package com.example.imdbclone.Activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.R
import com.example.imdbclone.Screen.DetailScreen
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.Screen.ShowItem
import com.example.imdbclone.Screen.ShowsScreen
import com.example.imdbclone.Screen.TopShowScreen
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController:NavHostController = rememberNavController()
            val showViewModel: MainViewModel = viewModel()
                NavHost(navController = navController, startDestination = Screens.SearchScreen.route){
                    composable(route= Screens.SearchScreen.route) {
                        SearchScreen(showViewModel, navigateToDetail = {
                            navController.currentBackStackEntry?.savedStateHandle?.set("ShowData",it)
                            navController.navigate(Screens.DetailScreen.route)
                        })

                    }
                    composable(route = Screens.DetailScreen.route) {
                        val showData = navController.previousBackStackEntry?.
                        savedStateHandle?.get<ShowDetails>("ShowData")
                        if (showData != null) {
                            DetailScreen(showData,navController)
                        }
                    }
                }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(showViewModel:MainViewModel,navigateToDetail: (ShowDetails) -> Unit) {

    val context = LocalContext.current as Activity
    var title by remember { mutableStateOf("") }

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

            Box(modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)) {
                
                
                TextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            tint = Color.White,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences,autoCorrect = true, imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        showViewModel.searchShow(title.lowercase())
                    }),
                    textStyle = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.White,
                        containerColor = Color.DarkGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(shape = RoundedCornerShape(40.dp)),
                    value = title,
                    onValueChange = {
                        title = it
                    }, singleLine = true
                )
            }
        }


        val searchViewModel by showViewModel.searchShows


    SearchStateScreen(searchViewModel,navigateToDetail)

    }

}



@Composable
fun SearchStateScreen(
    showState: MainViewModel.ShowState,
    navigateToDetail: (ShowDetails) -> Unit
) {

    Column(modifier = Modifier.wrapContentSize()) {

        when {

            showState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }

            showState.error != null -> {
                Text(showState.error, color = Color.White)
            }

            else -> {
                Log.d("TAG", "SearchStateScreen: "+showState.list)
                SearchShowsScreen(showState.list,navigateToDetail)
            }
        }
    }

}



@Composable
fun SearchShowsScreen(shows: List<ShowDetails>, navigateToDetail: (ShowDetails) -> Unit) {
    val context = LocalContext.current


    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())  // Add SVG decoder explicitly
        }
        .build()
    Column(
        modifier = Modifier
            .wrapContentSize(unbounded = false)
            .padding(8.dp)
    ) {

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                ,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            columns = GridCells.Fixed(3),
        ) {
            items(shows) { item ->

                SearchShowItem(item, navigateToDetail)

            }
        }

    }


}


@Composable
fun SearchShowItem(item: ShowDetails, navigateToDetail: (ShowDetails) -> Unit) {


    val context = LocalContext.current


    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())  // Add SVG decoder explicitly
        }.memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder().directory(context.cacheDir.resolve("image_cache"))
                .maxSizeBytes(512*1024*1024).build()
        }
        .build()

    Column(
        modifier = Modifier.padding(8.dp).clickable {
            try {

                navigateToDetail(item)
            } catch (e: Exception) {
                Log.d("nav", "ShowItem: " + e.message)
            }
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val painter = rememberAsyncImagePainter(
            model =  item.imageSet.verticalPoster?.w360 // Adjust as needed
            ,
            imageLoader = imageLoader
        )
        Image(
            painter = painter,
            modifier = Modifier
                .size(width = 140.dp, height = 160.dp).clip(RoundedCornerShape(8.dp)),
            contentDescription = item.title,
            contentScale = ContentScale.Crop

        )
    }


}

@Preview(showBackground = true)
@Composable
private fun SearchPrev() {
    IMDBCloneTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .height(150.dp)
                .width(120.dp)
                .background(Color.Black)) {
                Image(painter = painterResource(R.drawable.img), contentDescription = null, modifier = Modifier
                    .size(width = 120.dp, height = 150.dp)
                    ,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}


