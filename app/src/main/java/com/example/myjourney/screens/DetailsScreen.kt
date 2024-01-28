package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavHostController
import com.example.myjourney.R
import com.example.myjourney.screens.ui.theme.MyJourneyTheme

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context

class DetailsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val placeImg = intent.getIntExtra("placeImg", 0)
                    val placeName = intent.getStringExtra("placeName")
                    val placeDescription = intent.getStringExtra("placeDescription")
                    if (placeName != null && placeDescription != null) {
                        DetailScreen(placeImg, placeName, placeDescription)
                    }
                    Log.d("AAA", "$placeName" + "\n$placeDescription")
                }
            }
        }
    }
}

@Composable
fun DetailScreen(img: Int, name: String, description: String) {
    Scaffold { innerPadding ->
        DetailScreenContent(modifier = Modifier.padding(innerPadding), img, name, description)
    }
}

@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    pImg: Int,
    pName: String,
    pDescription: String
) {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 20.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Detail Screen" },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = pImg),
            contentDescription = null,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(10.dp)
                )

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
        MoreImages()
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
private fun MoreImages() {
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
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(id = R.drawable.more_1),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.more_2),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.more_3),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.more_4),
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPageView() {
    MyJourneyTheme {
        DetailScreen(0,"0","0")
    }
}