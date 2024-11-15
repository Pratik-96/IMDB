package com.example.imdbclone.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbclone.R
import com.example.imdbclone.ui.theme.DeepGray
import com.example.imdbclone.ui.theme.IMDBCloneTheme

class Authentication : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMDBCloneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = DeepGray)
                            .padding(innerPadding)
                    ) {
                        SignUp()
                    }


                }
            }
        }
    }
}

val sarabunFont = FontFamily(
    Font(R.font.sarabun_font, FontWeight.Normal)
)

@Composable
fun SignUp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DeepGray)
            .padding(24.dp)
    ) {

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(true) }

        Text("Get Started !!", color = Color.White, fontFamily = sarabunFont, fontSize = 24.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("SignUp", color = Color.White, fontFamily = sarabunFont, fontSize = 40.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Please fill the details below to create your account.",
            color = Color.White,
            fontFamily = sarabunFont,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = name,
            onValueChange = {
                name = it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person, null)
            },
            trailingIcon = {
                IconButton(onClick = {
                    name = ""
                }) {
                    if (name.isNotEmpty()){
                        Icon(imageVector = Icons.Filled.Clear, null)

                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Name")
            },
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedTrailingIconColor = Color.White,
                focusedLabelColor = Color.White,

                unfocusedLabelColor = Color.White,
                focusedLeadingIconColor = Color.White,
                focusedIndicatorColor = Color.White
            )

        )

        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = email,
            onValueChange = {
                email = it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, null)
            },
            trailingIcon = {
                IconButton(onClick = {
                    email = ""
                }) {
                    if (email.isNotEmpty()){
                        Icon(imageVector = Icons.Filled.Clear, null)

                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            },
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedTrailingIconColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedLeadingIconColor = Color.White,
                focusedIndicatorColor = Color.White
            )

        )

        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = password,
            onValueChange = {
                password = it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, null)
            },
            trailingIcon = {
                IconButton(onClick = {
//                    pas = ""
                }) {
                    Icon(imageVector = Icons.Filled.Visibility, null)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password")
            },
            colors = OutlinedTextFieldDefaults.colors().copy(
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedTrailingIconColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                focusedLeadingIconColor = Color.White,
                focusedIndicatorColor = Color.White
            )
            ,

        )

    }
}

