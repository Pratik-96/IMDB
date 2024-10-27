package com.example.imdbclone

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.DataClasses.VerticalPoster

@Composable
fun DetailScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()){
            val context = LocalContext.current


            // Configure Coil with an ImageLoader that includes the SvgDecoder
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    add(SvgDecoder.Factory())  // Add SVG decoder explicitly
                }
                .build()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data("https://cdn.movieofthenight.com/show/8863148/backdrop/horizontal/720.jpg?Expires=1760812068&Signature=N48E-NlNj79fDqaqoR5bozTnBhqqgyBMkcKe3wt17EgkJuPP8ZItJ7ztwU6b~Np27h3QVoZUoyTDdN4MCQrrXuskQtnTaN6d8-elcpS0FhzHYvautnnRsi69yOniAzNlMD3leddzmnXGPff4YR8BuwILlNr9hP8zqdYfUjvWaGkZ6Z1r0-f5Y6Z399mmiPDUSD45RJpwDoif22~eS9cQ48FLvGL~QSj7gwQ9vLJyansws7S-dt6xG6LnXQl4k0hXLU80RREbWKq-GguoVMXWgb3g~wIJ1VzovS0Vea~kRfKW9k-9xyBMJ4jDdBS5McPQHgGDbzzt0jsn8DUl0bg3iA__&Key-Pair-Id=KK4HN3OO4AT5R")
                    .size(Size.ORIGINAL)  // Adjust as needed
                    .build(),
                imageLoader = imageLoader
            )
            Image(
                painter = painter,
                contentDescription = null,

                contentScale = ContentScale.Crop

            )
        }
    }

}
