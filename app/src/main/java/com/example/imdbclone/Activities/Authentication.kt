package com.example.imdbclone.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdbclone.R
import com.example.imdbclone.Screen.ButtonText
import com.example.imdbclone.Screen.Loader
import com.example.imdbclone.Screen.Screens
import com.example.imdbclone.ui.theme.DeepGray
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

private lateinit var auth:FirebaseAuth





class Authentication : ComponentActivity() {
    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser!=null){
            setContent {
          val context = LocalContext.current as Activity
              context.startActivity(Intent(context,MainActivity::class.java))
              context.finish()
          }

      }
    }



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
                        val navHostController = rememberNavController()
                        NavHost(navController = navHostController, startDestination = Screens.SignUp.route){
                            composable(route = Screens.SignUp.route) {

                                SignUp {
                                    navHostController.navigate(Screens.Login.route)
                                }
                            }
                            composable(Screens.Login.route) {
                                Login(navHostController)
                            }
                        }
                    }


                }
            }
        }
    }
}

val sarabunFont = FontFamily(
    Font(R.font.sarabun_font, FontWeight.Normal)
)

fun isValidPassword(password: String): Boolean {
    if (password.length < 6) {
        return false // Password is too short
    }
    if (!password.any { it.isUpperCase() }) {
        return false // Password does not contain an uppercase letter
    }
    if (!password.any { !it.isLetterOrDigit() }) {
        return false // Password does not contain a special character
    }
    return true // Password meets all criteria
}

@Composable
fun SignUp(navigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = DeepGray)
            .padding(start = 24.dp, end = 24.dp)
    ) {

        val context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var isEmailValid by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }
        var isLoading by remember { mutableStateOf(false) }

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
                isEmailValid = isValidEmail(email)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, null)
            },
            isError = !isEmailValid,
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

        if (!isEmailValid){
            Spacer(Modifier.padding(8.dp))

            Text("Invalid Email", color = Color.Red, modifier = Modifier.align(Alignment.Start))
        }

        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = password,
            onValueChange = {
                password = it
                isPasswordValid = isValidPassword(password)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, null)
            },
            isError = !isPasswordValid,
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

            if (!isPasswordValid){
            Spacer(Modifier.padding(8.dp))

            Text("Password must contain: \n At least 6 characters \n At least 1 uppercase letter \n At least 1 special character.", color = Color.Red, modifier = Modifier.align(Alignment.Start))
        }

        val activityContext = LocalContext.current as Activity
        Spacer(Modifier.padding(8.dp))

        if (isLoading){
            Loader(Color.White)
        }else{
            Button(onClick = {
                isLoading = true
                if(isEmailValid && isPasswordValid && name.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()){

                    if (signUpWithEmailAndPassword(name,email,password,context,activityContext)){
                        isLoading = false
                    }else{
                        isLoading = false
                    }


                }else{
                    isLoading  = false
                }

            }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp), colors = ButtonDefaults.textButtonColors(contentColor = Color.Black, containerColor = Color.White)) {
//            Text("Create Account", modifier = Modifier.padding(4.dp), fontFamily = sarabunFont, fontSize = 18.sp)

                ButtonText("Create Account")

            }
        }

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Already have an Account ? ")
            }
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Login")
            }
        }
        Spacer(Modifier.padding(8.dp))
        Text(text = annotatedText, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .clickable { navigateToLogin() }, color = Color.White, fontSize = 16.sp, fontFamily = sarabunFont)



        

    }
}


fun signUpWithEmailAndPassword(
    name: String,
    email: String,
    password: String,
    context: Context,
    activityContext: Activity
): Boolean {
    auth =FirebaseAuth.getInstance()
    var isSuccessful = false
    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
        task->
        if (task.isSuccessful){
            val user = auth.currentUser
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()

            user?.updateProfile(profileUpdates)?.addOnCompleteListener{
                profileTask->
                if (profileTask.isSuccessful){
                    isSuccessful = true
                    Toast.makeText(context,"SignUp Successful!!",Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context,GenreActivity::class.java))
                    activityContext.finish()
                }
            }
        }else{
            Toast.makeText(context,"SignUp Failed: ${task.exception}",Toast.LENGTH_SHORT).show()
        }
    }

    return isSuccessful

}

fun isValidEmail(email: String): Boolean {
    val emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$".toRegex()
    return email.matches(emailPattern)
}

@Composable
fun Login(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = DeepGray)
            .padding(start = 24.dp, end = 24.dp)
    ) {

        val context = LocalContext.current
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var isEmailValid by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }
        var isLoading by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.padding(top = 24.dp))
        Text("Welcome Back !!", color = Color.White, fontFamily = sarabunFont, fontSize = 24.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Login", color = Color.White, fontFamily = sarabunFont, fontSize = 40.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Please fill the details below to login.",
            color = Color.White,
            fontFamily = sarabunFont,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = email,
            onValueChange = {
                email = it
                isEmailValid = isValidEmail(email)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, null)
            },
            isError = !isEmailValid,
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

        if (!isEmailValid){
            Spacer(Modifier.padding(8.dp))

            Text("Invalid Email", color = Color.Red, modifier = Modifier.align(Alignment.Start))
        }

        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = password,
            onValueChange = {
                password = it
                isPasswordValid = isValidPassword(password)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, null)
            },
            isError = !isPasswordValid,
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

        if (!isPasswordValid){
            Spacer(Modifier.padding(8.dp))

            Text("Password must contain: \n At least 6 characters \n At least 1 uppercase letter \n At least 1 special character.", color = Color.Red, modifier = Modifier.align(Alignment.Start))
        }

        val activityContext = LocalContext.current as Activity
        Spacer(Modifier.padding(8.dp))

            Button(onClick = {
                if(isEmailValid && isPasswordValid && password.isNotEmpty() && email.isNotEmpty()){

                    loginWithEmailAndPassword(email,password,context,activityContext)


                }

            }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp), colors = ButtonDefaults.textButtonColors(contentColor = Color.Black, containerColor = Color.White)) {
//            Text("Create Account", modifier = Modifier.padding(4.dp), fontFamily = sarabunFont, fontSize = 18.sp)

                ButtonText("Login")

            }


        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Don't have an Account ? ")
            }
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Create a Account.")
            }
        }
        Spacer(Modifier.padding(8.dp))
        Text(text = annotatedText, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .clickable { navHostController.popBackStack() }, color = Color.White, fontSize = 16.sp, fontFamily = sarabunFont)





    }
}

fun loginWithEmailAndPassword(email: String, password: String, context: Context, activityContext: Activity) {
    auth = FirebaseAuth.getInstance()
    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task->
        if (task.isSuccessful){
            val user = FirebaseAuth.getInstance().currentUser
            val name = user?.displayName?:" "
            Toast.makeText(context,"Welcome ${name} !!",Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context,MainActivity::class.java))
            activityContext.finish()
        }else{
            Toast.makeText(context,task.exception.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}


