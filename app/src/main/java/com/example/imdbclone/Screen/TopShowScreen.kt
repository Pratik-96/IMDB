package com.example.imdbclone.Screen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.ViewModels.MainViewModel

@Composable
fun TopShowScreen(viewModel: MainViewModel, navigateToDetail: (ShowDetails) -> Unit) {

    val netflixState by viewModel.netflixShowState
    val netflixMovieState by viewModel.netflixMovieState
    val appleState by viewModel.appleShowState
    val appleMovieState by viewModel.appleMovieState
    val primeState by viewModel.primeShowDetails
    val primeMovieState by viewModel.primeMovieDetails
    val hostarState by viewModel.hotstarShowDetails





    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),

    ) {
//        item { StateScreen(title = "Top Netflix Shows", netflixState, navigateToDetail) }
//        item { StateScreen(title = "Top Netflix Movies", netflixMovieState,navigateToDetail) }
//        item { StateScreen(title = "Top Apple Tv Shows", appleState,navigateToDetail) }
//        item { StateScreen(title = "Top Apple Tv Movies", appleMovieState,navigateToDetail) }
//        item { StateScreen(title = "Top Shows on Prime", primeState,navigateToDetail) }
//        item { StateScreen(title = "Top Movies on Prime", primeMovieState,navigateToDetail) }
//        item { StateScreen(title = "Top Shows on Hotstar", hostarState,navigateToDetail) }
    }
}

@Composable
fun StateScreen(
    title: String,
    showState: MainViewModel.ShowState,
    navigateToDetail: (ShowDetails) -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        when {

            showState.loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }

            showState.error != null -> {
                Text(showState.error, color = Color.White)
            }

            else -> {
                ShowsScreen(showState.list, title, navigateToDetail)
            }
        }
    }

}


@Composable
fun ShowsScreen(shows: List<ShowDetails>, title: String, navigateToDetail: (ShowDetails) -> Unit) {
    val context = LocalContext.current

    val logo = shows.get(0).streamingOptions?.`in`?.get(0)?.service?.imageSet?.darkThemeImage

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
        Row(
            modifier = Modifier
                .wrapContentSize(unbounded = false)
                .padding(8.dp)
        ) {

            // Use the custom ImageLoader with the ImageRequest
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(logo)
                    .size(Size.ORIGINAL)  // Adjust as needed
                    .build(),
                imageLoader = imageLoader
            )
            Image(
                painter = painter,
                modifier = Modifier.size(width = 50.dp, height = 24.dp),
                contentDescription = title,
                contentScale = ContentScale.Crop

            )

            Spacer(Modifier.padding(4.dp))
            Text(text = title, fontSize = 16.sp, color = Color.White, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
        }


        LazyHorizontalGrid(
            modifier = Modifier
                .wrapContentSize(unbounded = false)
                .height(80.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            rows = GridCells.Fixed(1)
        ) {
            items(shows) { item ->

                ShowItem(item, navigateToDetail)

            }
        }

    }


}


@Composable
fun ShowItem(item: ShowDetails, navigateToDetail: (ShowDetails) -> Unit) {




    val context = LocalContext.current


    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    Column(
        modifier = Modifier.clickable {
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
    }


}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {


}


