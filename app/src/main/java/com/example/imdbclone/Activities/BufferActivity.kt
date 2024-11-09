package com.example.imdbclone.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdbclone.DataClasses.ShowDetails
import com.example.imdbclone.Screen.DetailScreen
import com.example.imdbclone.Screen.LoadingScreen
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ui.theme.HotstarBackground
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class BufferActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMDBCloneTheme {
                val viewModel: HotstarViewModel = viewModel()
                val navController = rememberNavController()
                val id = intent.getStringExtra("id")
                id?.let { viewModel.fetchShowId(it) }
                val state = viewModel.marvelShows.value
                Column(
                    modifier = Modifier.fillMaxSize().background(HotstarBackground),
                    verticalArrangement = Arrangement.Center
                ) {
                }
                when {

                    state.loading -> {
                        LoadingScreen()
                    }

                    state.error != null -> {
                        Text(state.error)
                    }

                    else -> {
//
                        state.item?.let {
                            DetailScreen(
                                data = it,
                                navHostController = navController
                            )
                        }


                    }
                }
            }
        }
    }
}

