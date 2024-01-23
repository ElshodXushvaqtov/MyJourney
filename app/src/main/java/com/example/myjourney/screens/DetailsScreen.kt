package com.example.myjourney.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
                    DetailsPage()
                }
            }
        }
    }
}

@Composable
fun DetailsPage() {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Coming Soon!", style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
                color = Color(0xFF452933)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPageView() {
    MyJourneyTheme {
        DetailsPage()
    }
}