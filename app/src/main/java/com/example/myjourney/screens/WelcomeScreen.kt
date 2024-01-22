package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjourney.R
import com.example.myjourney.screens.ui.theme.MyJourneyTheme

private lateinit var intent:Intent
@SuppressLint("StaticFieldLeak")
private lateinit var context: Context

class WelcomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Welcome()
                }
            }
        }
    }
}

@Composable
fun Welcome() {
    context = LocalContext.current
    intent=Intent(context,SignInScreen::class.java)
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_splash),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Button(
            onClick = { context.startActivity(intent)},
            modifier = Modifier
                .padding(bottom = 56.dp)
                .size(220.dp, 76.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(72.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF65734))
        ) {
            Text(
                text = "Sign In",
                style = TextStyle(
                    fontFamily = gilroy,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W700
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
            Icon(
                painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    MyJourneyTheme {
        Welcome()
    }
}