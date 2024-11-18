package com.example.imdbclone.Screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.imdbclone.Activities.BufferActivity
import com.example.imdbclone.Activities.sarabunFont
import com.example.imdbclone.DataClasses.SavedShowDetails
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.ViewModels.MainViewModel
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth


@Composable
fun ListScreen() {

    val viewModel:MainViewModel = viewModel()
    val state = viewModel.fetchList.value
    when{
        state.loading->{
            Loader(Color.White)
        }
        else->{

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black), columns = GridCells.Fixed(1)
            ) {
                items(state.list) { show ->

                    ListItem(show)

                }
            }
        }
    }
}


@Composable
fun ListItem(showDetails: SavedShowDetails) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context,BufferActivity::class.java)
                intent.putExtra("id",showDetails.id)
                context.startActivity(intent)
            }
            .background(Color.Black)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                showDetails.horizontalImage,
                null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .width(150.dp)
                    .height(80.dp)
            )
            Text(
                text = showDetails.title.toString(),
                color = Color.White,
                fontFamily = sarabunFont,
                fontSize = 16.sp,
                modifier = Modifier.padding(8.dp).weight(1f),
                fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.White, CircleShape) // Add circular border
                .clip(CircleShape).defaultMinSize(minWidth = 20.dp)
                .clickable {
                    showDialog = true

                }
            ) {
                if (showDialog) {
                    Dialog({ showDialog = false }, showDetails)
                }
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun Dialog(onDismiss: () -> Unit, data: SavedShowDetails) {

    Dialog(onDismissRequest = onDismiss) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            ImportantText("Stream ${data.title} on:")
            for (i in 0 until (data.serviceMetaData?.size?:0)) {
                data.serviceMetaData?.get(i)?.let {
                    DialogLogo(
                        it
                    )

                }

            }
//            ImportantText("Rent on:")
//            for (i in 0 until (data.streamingOptions?.`in`?.size ?: 0)) {
//                if (data.streamingOptions?.`in`?.get(i)?.type.equals("rent")) {
//                    data.streamingOptions?.`in`?.get(i)?.let {
//                        DialogLogo(
//                            it
//                        )
//                    }
//                }
//
//            }

        }
    }
}

