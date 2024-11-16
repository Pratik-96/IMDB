package com.example.imdbclone.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbclone.R
import com.example.imdbclone.Screen.ButtonText
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
            .fillMaxSize().verticalScroll(rememberScrollState())
            .background(color = DeepGray)
            .padding(start = 24.dp, end = 24.dp)
    ) {

        val context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.padding(top = 24.dp))
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
                Text("Name", fontFamily = sarabunFont, color = Color.White)
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
            , singleLine = true


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
                Text("Email", fontFamily = sarabunFont, color = Color.White)
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
            , singleLine = true

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

                val image = if(passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff


                IconButton(onClick = {
                    passwordVisible =! passwordVisible
                }) {
                    Icon(imageVector = image, null)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Password", fontFamily = sarabunFont, color = Color.White)
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
            , singleLine = true
            , visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        val activityContext = LocalContext.current as Activity
        Spacer(Modifier.padding(8.dp))
        Button(onClick = {
            context.startActivity(Intent(context,MainActivity::class.java))
            activityContext.finish()
        }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp), colors = ButtonDefaults.textButtonColors(contentColor = Color.Black, containerColor = Color.White)) {
//            Text("Create Account", modifier = Modifier.padding(4.dp), fontFamily = sarabunFont, fontSize = 18.sp)
                ButtonText("Create Account")
        }


        

    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPrev() {
    IMDBCloneTheme {
        SignUp()
    }
}

