package com.example.imdbclone.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.provider.FontsContractCompat.Columns
import com.example.imdbclone.Screen.ImportantText
import com.example.imdbclone.Screen.LargeText
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class Authentication:ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMDBCloneTheme {

            }
        }
    }
}

@Composable
fun SignUp() {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray)){
        
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthPrev() {
    IMDBCloneTheme {
        SignUp()
    }
}