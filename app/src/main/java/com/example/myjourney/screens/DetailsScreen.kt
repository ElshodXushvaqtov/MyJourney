package com.example.myjourney.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myjourney.R
import com.example.myjourney.screens.ui.theme.MyJourneyTheme

class DetailsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val placeImg = intent.getStringExtra("placeImg")
                    val placeName = intent.getStringExtra("placeName")
                    val placeDescription = intent.getStringExtra("placeDescription")
                    val images = intent.getStringArrayExtra("moreImages")
                    if (placeImg != null && placeName != null && placeDescription != null && images != null) {
                        DetailScreen(placeImg, placeName, placeDescription, images)
                    }
                    Log.d("AAA", "$placeName" + "\n$placeDescription")
                }
            }
        }
    }
}

@Composable
fun DetailScreen(img: String, name: String, description: String, images: Array<String>?) {
    Scaffold { innerPadding ->
        DetailScreenContent(
            modifier = Modifier.padding(innerPadding),
            img,
            name,
            description,
            images
        )
    }
}

@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    pImg: String,
    pName: String,
    pDescription: String,
    moreImages: Array<String>?
) {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 15.dp)
            .fillMaxSize()
            .semantics { contentDescription = "Detail Screen" }
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = pImg,
            contentDescription = null,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .size(width = 400.dp, height = 350.dp),
            contentScale = ContentScale.FillBounds

        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pName, style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    color = Color(0xFF452933)
                )
            )
            Spacer(modifier = Modifier.weight(1F))
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "(4.8)", style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp,
                    color = Color(0xFF73848C)
                )
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = pDescription,
            style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color(0xFF73848C)
            ),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        MoreImages(moreImages)
        Spacer(modifier = Modifier.size(55.dp))
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Total Price", style = TextStyle(
                        fontFamily = gilroy,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = Color(0xFF201E1E)
                    )
                )
                Row {
                    Text(
                        text = "350", style = TextStyle(
                            fontFamily = gilroy,
                            fontWeight = FontWeight.W500,
                            fontSize = 30.sp,
                            color = Color(0xFF452933)
                        )
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "$", style = TextStyle(
                            fontFamily = gilroy,
                            fontWeight = FontWeight.W500,
                            fontSize = 30.sp,
                            color = Color(0xFFF65734)
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1F))
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(bottom = 56.dp)
                    .size(170.dp, 56.dp),
                shape = RoundedCornerShape(72.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF65734))
            ) {
                Text(
                    text = "Band Qilish", style = TextStyle(
                        fontFamily = gilroy,
                        fontWeight = FontWeight.W700,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
private fun MoreImages(more: Array<String>?) {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Spacer(modifier = Modifier.size(24.dp))
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "More Images", style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W700,
                fontSize = 14.sp,
                color = Color(0xFF452933)
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {


            Card(
                modifier = Modifier.size(width = 85.dp, height = 80.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(20)
            ) {
                AsyncImage(
                    model = more?.get(0),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Card(
                modifier = Modifier.size(width = 85.dp, height = 80.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(20)
            ) {
                AsyncImage(
                    model = more?.get(1),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Card(
                modifier = Modifier.size(width = 85.dp, height = 80.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(20)
            ) {
                AsyncImage(
                    model = more?.get(2),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Card(
                modifier = Modifier.size(width = 85.dp, height = 80.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(20)
            ) {

                AsyncImage(
                    model = more?.get(3),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPageView() {
    MyJourneyTheme {
//        DetailScreen(0, "0", "0")
    }
}