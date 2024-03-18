package com.example.myjourney.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.carappcompose.effects.AnimatedShimmer
import com.example.myjourney.RecommendItem
import com.example.myjourney.data.PlaceData
import com.example.myjourney.data.Places
import com.example.myjourney.data.UserPanel
import com.example.myjourney.screens.ui.theme.MyJourneyTheme
import kotlinx.coroutines.delay

class SavedScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Saved()
                }
            }
        }
    }
}

@Composable
fun Saved() {
    val context = LocalContext.current
    var places by remember { mutableStateOf<List<Places>>(emptyList()) }
    UserPanel.FavouriteGet { list ->
        PlaceData.FavouritesFilter(list) {
            places = it
            Log.d("", places.toString()+"\n")
        }
    }
    val placesLength: Int = places.size
    MyColumn(
        intent = Intent(context, DetailsScreen::class.java),
        placesLength = placesLength,
        places = places
    )
}

@Composable
fun MyColumn(intent: Intent, placesLength: Int, places: List<Places>) {
    var loading by remember { mutableStateOf(true) }
    LaunchedEffect(
        key1 = true,
        block = {
            delay(2000)
            loading = false
        }
    )


        if (places.isEmpty()) {
            EmptyListIcon()
        } else {
            if (loading) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(top = 100.dp, start = 10.dp, end = 10.dp)
                )
                {
                    items(placesLength) {
                        AnimatedShimmer()
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(
                        bottom = 200.dp,
                        start = 10.dp,
                        end = 10.dp,
                        top = 100.dp
                    )
                ) {
                    items(items = places) { item ->
                        item.name?.let { it1 ->
                            item.category?.let { it2 ->
                                item.description?.let { it3 ->
                                    item.img?.let { it4 ->
                                        RecommendItem(
                                            name = it1,
                                            category = it2,
                                            description = it3,
                                            imgUrl = it4,
                                            intent = intent
                                        )
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
fun EmptyListIcon() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/cb9658f7-56e1-418c-b9f9-c94d3af5d5b0/CXDVREveWd.lottie"))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        )
    }
}