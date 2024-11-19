package com.example.imdbclone.Screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.Activities.SearchStateScreen
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ViewModels.MainViewModel
import com.example.imdbclone.ViewModels.PrimeViewModel
import com.example.imdbclone.ui.ImageSlider
import com.example.imdbclone.ui.StudioLogos
import com.example.imdbclone.ui.Studios
import com.example.imdbclone.ui.theme.DeepGray
import com.example.imdbclone.ui.theme.HotstarBackground
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@Composable
fun PrimeScreen(navigateToDetail:(ShowDetails)->Unit) {

    val mainViewModel: MainViewModel = viewModel()
    val viewModel: PrimeViewModel = viewModel()
    viewModel.fetchTopShows("in","prime","prime",87)
    val showState = viewModel.topPrimeShows
    viewModel.fetchHindiMovies("in","prime","prime","movie",70,"","hindi")
    val movieState = viewModel.hindiMovies
    viewModel.fetchHindiSeries("in","prime","prime","series",70,"","hindi")
    val seriesState = viewModel.hindiShows

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        when {

            showState.value.loading -> {
                Column(modifier = Modifier.fillMaxSize()) {

                    LoadingLogo(
                        mainViewModel.items[3].url, Color.Black, Color.White
                    )
                    Loader(Color.White)
                }
            }

            showState.value.error != null -> {
                Text(showState.value.error.toString(), color = Color.White)
            }

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize()
                ) {
                    item { PrimeImageSlider (showState.value.list, navigateToDetail) }
                    item { SearchStateScreen(
                        movieState.value,
                        navigateToDetail = navigateToDetail,
                        vertical = false,
                        header = "Movies in Hindi",
                    ) }
                    item { StateScreen("Series in Hindi",seriesState.value,navigateToDetail)}

                }
            }

        }


    }
}

@Composable
fun Loader(color: Color) {
    Box(modifier = Modifier.wrapContentSize()){

        CircularProgressIndicator(color = color, modifier = Modifier.align(Alignment.Center))
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PrimeImageSlider(data: List<ShowDetails>, navigateToDetail: (ShowDetails) -> Unit) {


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}

    val viewModel: HotstarViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {


        val pagerState = rememberPagerState(initialPage = 0)
        LaunchedEffect(Unit) {
            while (true) {
                delay(5000)
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % data.size)
            }
        }



        HorizontalPager(
            count = data.size,
            state = pagerState,
            itemSpacing = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->


            var link = data[page].streamingOptions?.`in`?.get(0)?.link.toString()
            for (i in 0 until data[page].streamingOptions?.`in`?.size!!) {
                if (data[page].streamingOptions?.`in`?.get(i)?.service?.id.equals("prime")) {
                    link = data[page].streamingOptions?.`in`?.get(i)?.link.toString()
                    break
                }
            }


            Column {
                val img = data[page].imageSet.horizontalPoster?.w720
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .height(250.dp)
                ) {

                    AsyncImage(
                        img,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(link)
                                )
                                launcher.launch(intent)
                            }

                    


                    )

                    Box(modifier = Modifier.wrapContentSize().align(Alignment.BottomEnd).background(
                        Color.Black.copy(0.3f))){

                        Text("Watch on Prime", color = Color.White, modifier = Modifier.padding(8.dp))
                    }


                }
            }
        }
    }


}


@Composable
fun LoadingLogo(url: String,background:Color,loaderColor:Color) {
    Column(
        modifier = Modifier.fillMaxSize().background(background),
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        // Redundant code make a fun
        // Configure Coil with an ImageLoader that includes the SvgDecoder
        val imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())  // Add SVG decoder explicitly
            }
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder().directory(context.cacheDir.resolve("image_cache"))
                    .maxSizeBytes(512 * 1024 * 1024).build()
            }
            .build()
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context)
                .data(url)
                .size(Size.ORIGINAL)  // Adjust as needed
                .build(),
            imageLoader = imageLoader
        )
        Image(
            painter = painter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp),
            contentDescription = null
        )

        CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally), color = loaderColor)

    }
}

