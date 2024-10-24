package com.example.imdbclone

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
fun TopShowScreen(viewState:MainViewModel.ShowState) {
    Log.d("TAG", "TopShowScreen: "+viewState.list)
}