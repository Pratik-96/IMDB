package com.example.imdbclone

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SearchScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {

    val context = LocalContext.current as Activity
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
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

            Box(modifier = Modifier.wrapContentSize().padding(12.dp)) {
                
                SearchBar(
                    query = TODO(),
                    onQueryChange = TODO(),
                    onSearch = TODO(),
                    active = TODO(),
                    onActiveChange = TODO()
                ) { }
                
                
                
//                TextField(
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Filled.Search,
//                            tint = Color.White,
//                            contentDescription = null
//                        )
//                    },
//                    colors = TextFieldDefaults.textFieldColors(
//                        focusedTextColor = Color.White,
//                        containerColor = Color.DarkGray,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent
//                    ),
//
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentHeight()
//                        .clip(shape = RoundedCornerShape(40.dp)),
//                    value = name,
//                    onValueChange = {
//                        name = it
//                    }, singleLine = true
//                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    IMDBCloneTheme {
        SearchScreen()
    }
}