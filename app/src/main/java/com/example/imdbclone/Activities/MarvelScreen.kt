package com.example.imdbclone.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imdbclone.R
import com.example.imdbclone.ui.theme.HotstarBackground
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class MarvelScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            IMDBCloneTheme {

                val marvelBg = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/5846/1515846-i-ebe96109b04f"
                val marvel = "https://img10.hotstar.com/image/upload/f_auto,q_90,w_3840/sources/r1/cms/prod/5845/1515845-t-b9843a60cd4f"
                Column(modifier = Modifier.fillMaxSize().background(HotstarBackground)) {
                    Box(modifier = Modifier.wrapContentSize()){
                        AsyncImage(
                           marvelBg,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(300.dp),
                            contentScale = ContentScale.Crop
                        )
                        AsyncImage(
                            marvel,
                            contentDescription = null,
                            modifier = Modifier.height(140.dp).fillMaxWidth().align(Alignment.BottomCenter),
                            contentScale = ContentScale.Crop
                        )

                    }
                }

            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun Prec() {
    IMDBCloneTheme {

    }
}
