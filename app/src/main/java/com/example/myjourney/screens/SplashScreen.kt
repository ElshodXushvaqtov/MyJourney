package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myjourney.R
import com.example.myjourney.screens.ui.theme.MyJourneyTheme
import kotlinx.coroutines.delay

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Splash()
                }
            }
        }
    }
}

@Composable
fun Splash() {
    context = LocalContext.current
    val lottieComp by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_loading))
    val isPlaying by remember {
        mutableStateOf(true)
    }
    val progress by animateLottieCompositionAsState(composition = lottieComp, isPlaying = isPlaying)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = lottieComp,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize()
        )
    }
    LaunchedEffect(key1 = progress) {
        delay(4000)
        val intent = Intent(context,WelcomeScreen::class.java)
        context.startActivity(intent)
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyJourneyTheme {
        Splash()
    }
}