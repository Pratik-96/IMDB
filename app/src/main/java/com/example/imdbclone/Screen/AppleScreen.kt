package com.example.imdbclone.Screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.R
import com.example.imdbclone.ViewModels.AppleViewModel
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ui.theme.Gray

@Composable
fun AppleScreen(navigateToDetail: (ShowDetails) -> Unit) {

    val mainViewModel:MainViewModel = viewModel()
    val appleViewModel: AppleViewModel = viewModel()
    appleViewModel.fetchAppleShows()
    val topShowState = appleViewModel.fetchTopShow.value
    appleViewModel.fetchAppleMovies()
    val topMovieState = appleViewModel.fetchTopMovie.value
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { }
    val context = LocalContext.current
    appleViewModel.fetchShowId("2093350")
    val state = appleViewModel.fetchShow.value
    when{
        state.loading->{
            LoadingLogo(mainViewModel.items.get(4).url, Color.Black,Color.White)
        }
        state.error!=null->{
            Text(state.error)
        }
        else->{
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)) {

                item{TopShow(state)}

                item { AppleShowState(topShowState,navigateToDetail,"Top Chart: TV Shows") }
                item { AppleShowState(topMovieState,navigateToDetail,"Top Chart: Movies") }
            }
        }
    }


}

@Composable
fun AppleShowState(state:MainViewModel.ShowState, navigateToDetail: (ShowDetails) -> Unit, header: String) {
    //TODO: Add separate show state

    Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        when{
            state.loading->{
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally), color = Color.White)
            }
            state.error!=null->{
                ImportantText(state.error)
            }
            else->{
                TopShowList(state.list,navigateToDetail,header)
            }
        }
    }

}

@Composable
fun TopShowList(shows: List<ShowDetails>,navigateToDetail: (ShowDetails) -> Unit,header: String) {

    Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.Start) {
        ImportantText(header)
        LazyHorizontalGrid(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), rows = GridCells.Fixed(1), horizontalArrangement = Arrangement.SpaceEvenly) {
            itemsIndexed(shows){index,item->

                AppleTopShowItem(item,index+1,navigateToDetail)

            }
        }
    }

}

@Composable
fun AppleTopShowItem(
    item: ShowDetails,
    index: Int,
    navigateToDetail: (ShowDetails) -> Unit
) {
    val context = LocalContext.current


    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
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
            model =  item.imageSet.horizontalPoster?.w360 // Adjust as needed
            ,
            imageLoader = imageLoader
        )
        Image(
            painter = painter,
            modifier = Modifier
                .size(width = 150.dp, height = 80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = item.title,
            contentScale = ContentScale.Crop

        )

        Row(modifier = Modifier.width(150.dp), horizontalArrangement = Arrangement.Start) {

                Text("${index}", fontWeight = FontWeight.ExtraBold, fontSize = 48.sp, modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically))
                Column(modifier = Modifier.wrapContentSize()) {
                    Text(item.title, fontSize = 16.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold, modifier = Modifier.padding(4.dp))
                    item.genres.get(0)?.name?.let { Text(it, color = Gray, fontSize = 14.sp,modifier = Modifier.padding(start = 4.dp)) }
                }



        }
    }
}

@Composable
fun TopShow(state: MainViewModel.SearchShowState) {

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth().height(600.dp), contentAlignment = Alignment.BottomCenter
    ) {

        val showLink = state.item?.streamingOptions?.`in`?.get(0)?.link
        AsyncImage(
//            painter = painterResource(R.drawable.ghosted_img),
            (state.item?.imageSet?.verticalBackdrop?.w480),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )


        val genresList: List<String> = listOf("Action", "2023", "1hr 56 min")
        val genres = genresList.joinToString(" • ") { it }
        Column(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .background(
                    brush =
                    Brush
                        .verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.6f)
                            ),
                            startY = 0f, // Start at the top
                            endY = 100f
                        )
                )
                .shadow(elevation = 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("HINDI-DUBBED VERSION AVAILABLE", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(4.dp))

            Image(painter = painterResource(R.drawable.ghosted), null)

            Box(
                modifier = Modifier.wrapContentSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    genres,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .align(Alignment.BottomCenter),

                    )
            }
            Box(modifier = Modifier.wrapContentSize()) {
                Button(
                    onClick = {


                        launcher.launch(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(showLink)
                            )
                        )


                    }, modifier = Modifier
                        .height(55.dp)
                        .padding(8.dp),
//                            .shadow(shape = RoundedCornerShape(4.dp), elevation = 10.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    ), shape = RoundedCornerShape(8.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Play"
                    )
                    ButtonText("Watch on Apple TV+")

                }


            }
            state.item?.let { Text(it.overview, fontFamily = FontFamily.SansSerif,maxLines = 3,overflow = TextOverflow.Ellipsis, color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(16.dp)) }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {

                Text("4k",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp),
                    color = Gray,
                    fontSize = 12.sp
                )

                Image(painterResource(R.drawable.dolby_vision),null, modifier = Modifier
                    .height(20.dp)
                    .padding(start = 4.dp))
                Image(painterResource(R.drawable.dolby_atmos),null, modifier = Modifier
                    .height(20.dp)
                    .padding(start = 4.dp))
            }



        }


    }
}
