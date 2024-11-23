package com.example.imdbclone.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.imdbclone.Screen.ButtonText
import com.example.imdbclone.Screen.ImportantText
import com.example.imdbclone.ui.theme.DeepGray
import com.example.imdbclone.ui.theme.IMDBCloneTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

private lateinit var auth: FirebaseAuth

data class checkBoxInfo(
    var isChecked: Boolean,
    val text: String,
    val img: String
)

val genres = mutableListOf(
    checkBoxInfo(
        isChecked = false,
        text = "Action",
        img = "https://m.media-amazon.com/images/I/71qjLOFJ1JL.jpg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Sci-Fi",
        img = "https://m.media-amazon.com/images/I/61wrhEawgQL._AC_UF1000,1000_QL80_.jpg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Adventure",
        img = "https://cdn11.bigcommerce.com/s-yzgoj/images/stencil/1280x1280/products/2919271/5944675/MOVEB46211__19379.1679590452.jpg?c=2"
    ), checkBoxInfo(
        isChecked = false,
        text = "Animation",
        img = "https://lumiere-a.akamaihd.net/v1/images/p_cars_19643_4405006d.jpeg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Comedy",
        img = "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTM0o8m4ZliAmrjobwdkf2DXTMDLzZSaZBerQXhvHNBUILGmLnh1LHHtttMW3O7A5qA7stLmw"
    ), checkBoxInfo(
        isChecked = false,
        text = "Crime",
        img = "https://rukminim2.flixcart.com/image/850/1000/k0h12fk0/poster/r/e/m/medium-joker-movie-poster-for-room-office-13-inch-x-19-inch-original-imafk9cfehvuwyha.jpeg?q=90&crop=false"
    ), checkBoxInfo(
        isChecked = false,
        text = "Documentary",
        img = "https://cms.bbcearth.com/sites/default/files/2020-11/Planet%20Earth%20II%20tile_592x888px.png"
    ), checkBoxInfo(
        isChecked = false,
        text = "Drama",
        img = "https://m.media-amazon.com/images/I/71JxA6I+sgL._AC_UF894,1000_QL80_.jpg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Fantasy",
        img = "https://media.harrypotterfanzone.com/philosophers-stone-20-years-of-movie-magic-poster.jpg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Horror",
        img = "https://m.media-amazon.com/images/I/81dzWlccrGS.jpg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Romance",
        img = "https://m.media-amazon.com/images/I/61VCj3ofVIL._AC_UF894,1000_QL80_.jpg"
    ), checkBoxInfo(
        isChecked = false,
        text = "Thriller",
        img = "https://img.posterstore.com/zoom/wb0037-8batman-thedarkknightrises50x70.jpg"
    )
)


class GenreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val checkBoxes = remember {
                genres
            }
            IMDBCloneTheme {
                Scaffold { innerPadding ->

                    var selectedGenres = mutableListOf("")
                    var count by remember { mutableStateOf(0) }
                    val context = LocalContext.current as Activity


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(DeepGray),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        ImportantText("Build Your Home Page")
                        Text(
                            "Choose your favourite genres",
                            color = Color.White,
                            fontFamily = sarabunFont
                        )


                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){



                            Spacer(Modifier.padding(50.dp))

                            LazyVerticalGrid(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(DeepGray),
                                columns = GridCells.Fixed(3),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {


                                items(checkBoxes, key = { it.text }) { item ->
                                    GenreItem(item)
                                }

                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Button(
                                    onClick = {
                                        count = 0
                                        selectedGenres.clear()
                                        for (item in checkBoxes) {
                                            if (item.isChecked) {
                                                count++
                                                selectedGenres.add(item.text)
                                            }
                                        }
                                        if (count >= 3) {
                                            auth = FirebaseAuth.getInstance()
                                            val uid = auth.currentUser?.uid?:"null"
                                            val database = FirebaseDatabase.getInstance().reference
                                            database.child("user_data").child(uid).child("selectedGenres").setValue(selectedGenres).addOnCompleteListener{task->

                                                if(task.isSuccessful){
                                                    val intent = Intent(context,MainActivity::class.java)
                                                    context.startActivity(intent)
                                                    context.finish()
                                                    Toast.makeText(context,"Genres Uploaded",Toast.LENGTH_SHORT).show()
                                                }else{
                                                    Toast.makeText(context,task.exception?.message,Toast.LENGTH_SHORT).show()

                                                }

                                            }

//                                            Toast.makeText(
//                                                context,
//                                                selectedGenres.toString(),
//                                                Toast.LENGTH_SHORT
//                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Select at least 3 genres",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        }
                                    }, modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp).align(Alignment.BottomCenter)
                                        .clip(
                                            RoundedCornerShape(4.dp)
                                        ),
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = Color.Black,
                                        containerColor = Color.White
                                    )
                                ) {
                                    ButtonText("Continue")
                                }
                            }


                        }
                        }


                }
            }
        }
    }
}


@Composable
fun GenreItem(item: checkBoxInfo) {

    var checked = remember { mutableStateOf(false) }
    checked.value = item.isChecked

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .clickable {
                item.isChecked = !item.isChecked
                checked.value = item.isChecked
            }
            .then( // Apply border conditionally
                if (checked.value) {
                    Modifier.border(2.dp, shape = RoundedCornerShape(16.dp), color = Color.White)
                } else {
                    Modifier// No border if not checked
                }
            )
    ) {
        AsyncImage(
            item.img, modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .size(width = 130.dp, height = 150.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        Text(
            item.text,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = sarabunFont,
            modifier = Modifier.padding(4.dp)
        )

    }
}
