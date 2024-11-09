package com.example.imdbclone.Screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.imdbclone.Activities.BufferActivity
import com.example.imdbclone.DataClasses.MarvelData
import com.example.imdbclone.DataClasses.MarvelList
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ui.theme.HotstarBackground
import kotlinx.coroutines.launch


@Composable
fun Screen(navigateToDetail: (ShowDetails) -> Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(HotstarBackground)) {
        item { Poster() }
        item { MarvelShowsScreen(MarvelList,navigateToDetail,"MCU Infinity Saga") }
    }
}


@Composable
fun MarvelShowsScreen(shows: List<MarvelData>, navigateToDetail: (ShowDetails) -> Unit, header:String) {
    val context = LocalContext.current



    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(HotstarBackground)
            .padding(8.dp)
    ) {

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
            rows = GridCells.Fixed(1),
        ) {
            items(shows) { item ->

                MarvelShowItem(item, navigateToDetail)

            }
        }




    }
}

@Composable
fun LoadingScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(HotstarBackground), verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator(
            Modifier
                .align(Alignment.CenterHorizontally), color = Color.White)
    }
}

var list : List<ShowDetails> = emptyList<ShowDetails>()

@Composable
fun MarvelShowItem(item: MarvelData, navigateToDetail: (ShowDetails) -> Unit) {


    val viewModel: HotstarViewModel = viewModel()
    val marvelState = viewModel.marvelShows.value
    val context = LocalContext.current
//

    when{
        !marvelState.loading->{
            Log.d("TAG",marvelState.item.toString() )
        }
    }
    Column(
        modifier = Modifier
            .padding(2.dp)

            .clickable {


                val intent = Intent(context,BufferActivity::class.java)
                intent.putExtra("id",item.id)
                context.startActivity(intent)
//                viewModel.fetchShowId(item.id)

//
////                        marvelState.item?.let { navigateToDetail(it) }
//                marvelState.item?.let { list.add(it) }
//
//                Log.d("TAG", list.toString())


//                marvelState.item?.let { navigateToDetail(it) }

            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        AsyncImage(
            item.poster,
            modifier = Modifier
                .size(width = 140.dp, height = 210.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
        )
    }


}





@Composable
fun Poster() {
    val marvelBg =
        "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/5846/1515846-i-ebe96109b04f"
    val marvel =
        "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/5845/1515845-t-b9843a60cd4f"
    Box(modifier = Modifier.wrapContentSize()) {
        AsyncImage(
            marvelBg,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        AsyncImage(
            marvel,
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.Crop
        )

    }
}
