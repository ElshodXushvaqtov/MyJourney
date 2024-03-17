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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
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
    val sharedPreference = context.getSharedPreferences("myShared", Context.MODE_PRIVATE)
    val isLogged =
        context.getSharedPreferences("myShared", Context.MODE_PRIVATE).getBoolean("isLogged", false)
    val progress by animateLottieCompositionAsState(composition = lottieComp, isPlaying = isPlaying)
    val iM = Intent(context, HomeScreen::class.java)
    val iS = Intent(context, SignInScreen::class.java)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = lottieComp,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(width = 200.dp, height = 200.dp)
        )
    }
    LaunchedEffect(key1 = progress) {
        delay(3500)
        if (isLogged) {
            iM.putExtra("uid", sharedPreference.getString("userID", ""))
            iM.putExtra("userPhoto", sharedPreference.getString("uPhoto", ""))
            iM.putExtra("userName", sharedPreference.getString("uName", ""))
            context.startActivity(iM)
        } else {
            context.startActivity(iS)
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SplashView() {
    MyJourneyTheme {
        Splash()
    }
}