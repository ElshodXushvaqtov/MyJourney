package com.example.myjourney

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myjourney.screens.DetailsScreen
import kotlin.math.log

@Composable
fun Item(
    name: String,
    img: String,
    description: String,
    category: String,
//    moreImages: Array<String>,
    context: Context,
    intent: Intent? = Intent(context, DetailsScreen::class.java)
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp)
            .height(150.dp)
            .clickable {
                if (intent != null) {
                    intent.putExtra("placeName", name)
                    intent.putExtra("placeImg", img)
                    intent.putExtra("placeDescription", description)
//                    intent.putExtra("Place Category", category)
//                    intent.putExtra("More Images", moreImages)
                }
                context.startActivity(Intent(context, DetailsScreen::class.java))
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = img,
                contentDescription = "Rick Roll",

                modifier = Modifier
                    .size(150.dp)
                    .border(
                        BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(15.dp),
                    ),


                contentScale = ContentScale.Crop,

                )
            Log.d("iName", name)
            Text(text = name)
        }
    }
}