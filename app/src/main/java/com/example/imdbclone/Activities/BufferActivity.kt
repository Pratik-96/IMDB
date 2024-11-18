package com.example.imdbclone.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.imdbclone.Screen.DetailScreen
import com.example.imdbclone.Screen.LoadingScreen
import com.example.imdbclone.ViewModels.HotstarViewModel
import com.example.imdbclone.ui.theme.DeepGray
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class BufferActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMDBCloneTheme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: HotstarViewModel = viewModel()
                    val navController = rememberNavController()
                    val id = intent.getStringExtra("id")
                    id?.let { viewModel.fetchShowId(it) }
                    val state = viewModel.marvelShows.value
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = DeepGray)
                            .padding(innerPadding).statusBarsPadding(),
                        verticalArrangement = Arrangement.Center
                    ) {
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
    }
}

