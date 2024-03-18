package com.example.myjourney

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.myjourney.data.PlaceData
import com.example.myjourney.data.Places
import com.example.myjourney.data.UserPanel

@Composable
fun RecommendItem(
    name: String,
    category: String,
    description: String,
    imgUrl: String,
    intent: Intent
) {
    val context = LocalContext.current
    var places by remember {
        mutableStateOf<List<Places>>(emptyList())
    }
    PlaceData.GetPlaces { list ->
        places = list
    }
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)

            .border(
                BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(12.dp),
            )
            .clickable {
                intent.putExtra("placeName", name)
                intent.putExtra("placeImg", imgUrl)
                intent.putExtra("placeDescription", description)
                intent.putExtra("placeCategory", category)
                context.startActivity(intent)
            }, colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        var isSaved by remember {
            mutableStateOf(false)
        }

        UserPanel.isFavourite(place_name = name) {
            isSaved = it
        }


        Column(modifier = Modifier) {

            Image(
                painter = rememberAsyncImagePainter(imgUrl),
                contentDescription = "gfg image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )


            Row(modifier = Modifier.align(Alignment.End)) {
                IconButton(
                    onClick = {
                        if (!isSaved) {
                            UserPanel.FavouritesCreate(
                                name
                            )
                            Toast.makeText(context,"Band qilindi",Toast.LENGTH_SHORT).show()
                        } else {
                            UserPanel.FavouritesDelete(name)
                            isSaved = !isSaved
                            Toast.makeText(context,"O'chirib tashlandi",Toast.LENGTH_SHORT).show()
                        }
                    },
                ) {
                    if (isSaved) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_favorite_24),
                            contentDescription = null,
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                            contentDescription = null,
                        )
                    }

                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = name.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = category.uppercase(),
                modifier = Modifier.fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color(255, 165, 0)
            )
            Spacer(modifier = Modifier.height(3.dp))
        }
    }
}
