package com.example.imdbclone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.request.ImageRequest
import coil.size.Size
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.ui.theme.Gray
import com.example.imdbclone.ui.theme.IMDBCloneTheme

@Composable
fun DetailScreen(data:ShowDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
        ) {
            BackgroundPoster(data.imageSet.horizontalBackdrop?.w720 ?: "")//populate

        }
        Text(
            text = data.title,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (data.showType.equals("movie")) {
                NormalText(data.releaseYear) // modify
                NormalText("${data.runtime}m")
            }else{
                NormalText(data.firstAirYear)
                NormalText("${data.seasonCount} Season")
            }
            NormalText(data.genres.get(0)?.name?:"")
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
            Text(text = "${data.rating}%", color = Gray, modifier = Modifier.padding(top = 8.dp))

        }

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ImportantText("Streaming on: ")
            Logo(data.streamingOptions?.`in`?.get(0)?.service?.imageSet?.darkThemeImage?:"")


            Box(modifier = Modifier
                .padding(8.dp)
                .border(1.dp, color = Gray, RoundedCornerShape(4.dp))){
                Text(data.streamingOptions?.`in`?.get(0)?.quality.toString().toUpperCase(), fontWeight = FontWeight.Bold, modifier = Modifier.padding(1.dp), color = Gray, fontSize = 12.sp)
            }
        }
        Button(
            onClick = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(
                    RoundedCornerShape(4.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.White
            )

        ) {
            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "Play")
            ButtonText("WATCH")
        }
        NormalText(data.overview)

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val cast: List<String> = data.cast
            val castString = cast.joinToString(",") { it }
            Text(
                "Starring: ",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                fontWeight = FontWeight.Bold
            )
            SmallText(castString)

        }

        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if(data.showType.equals("movie")){
                val directors: List<String> = data.directors
                val directorString = directors.joinToString(",") { it }
                Text(
                    "Directors: ",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                SmallText(directorString)
            }else{
                val creators: List<String> = data.creators
                val createrString = creators.joinToString(",") { it }
                Text(
                    "Creators: ",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                SmallText(createrString)
            }
        }
    }


}


@Composable
fun Logo(url: String) {
    val context = LocalContext.current
    // Redundant code make a fun

    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())  // Add SVG decoder explicitly
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
            .padding(top = 8.dp)
            .size(width = 50.dp, height = 24.dp),
        contentDescription = null,
        contentScale = ContentScale.Crop

    )
}

@Composable
fun BackgroundPoster(url:String) {
    val context = LocalContext.current


    // Configure Coil with an ImageLoader that includes the SvgDecoder
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())  // Add SVG decoder explicitly
        }
        .build()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(url)
            // Adjust as needed
            .build(),
        imageLoader = imageLoader
    )
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        contentScale = ContentScale.Crop

    )
}


@Composable
fun ButtonText(text: String) {
    Text(
        text = text,
        color = Color.Black,
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp,
        modifier = Modifier.padding(8.dp),
        fontWeight = FontWeight.Bold
    )

}

@Composable
fun NormalText(text: String) {
    Text(text = text, color = Color.Gray, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
}

@Composable
fun SmallText(text: String) {
    Text(text = text, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(8.dp))
}

@Composable
fun ImportantText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp,
        modifier = Modifier.padding(8.dp),
        fontWeight = FontWeight.Bold
    )
}

