package com.example.myjourney

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myjourney.screens.DetailsScreen

@Composable
fun Item(
    name: String,
    img: Int,
    description: String,
    category: String,
    moreImages: Array<String>,
    context: Context,
    intent: Intent? = Intent(context, DetailsScreen::class.java)
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(150.dp)
            .height(150.dp)
            .clickable {
                context.startActivity(Intent(context, DetailsScreen::class.java))
                if (intent != null) {
                    intent.putExtra("Place Name", name)
                    intent.putExtra("Place Img", img)
                    intent.putExtra("Place Description", description)
                    intent.putExtra("Place Category", category)
                    intent.putExtra("More Images", moreImages)
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = img),


                contentDescription = "Rick Roll",

                modifier = Modifier
                    .size(150.dp)
                    .border(
                        BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(12.dp),
                    ),


                contentScale = ContentScale.Crop,

                )

        }
    }
}