package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myjourney.Item
import com.example.myjourney.data.PlaceData
import com.example.myjourney.data.Places
import com.example.myjourney.ui.theme.MyJourneyTheme

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context

class SearchScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun MainSearch() {
    var searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var places by remember {
        mutableStateOf<List<Places>>(emptyList())
    }

    PlaceData.GetPlaces { list ->
        places = list
    }

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Search(state = searchText, placeholder = "Search places ")

    }

    Column(modifier = Modifier.padding(top = 90.dp))
    {
        val searchedPlaces = places.filter {
            it.name.contains(searchText.value.text, ignoreCase = true) == true
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(bottom = 100.dp, start = 10.dp, end = 10.dp)
        ) {
            items(items = searchedPlaces) { item ->
                item.name.let { it1 ->
                    item.description.let { it2 ->
                        item.category.let { it3 ->
                            item.img.let { it4 ->
                                item.moreImages.let { it5 ->
                                    Item(
                                        name = it1,
                                        img = it4,
                                        description = it2,
                                        category = it3,
                                        moreImages = it5,
                                        context = context
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(state: MutableState<TextFieldValue>, placeholder: String) {

    TextField(
        value = state.value,

        onValueChange = { value ->

            state.value = value
        },


        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(1.dp, color = Color.Black, RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White),

        placeholder = {
            Text(
                text = placeholder,
                fontWeight = FontWeight.SemiBold
            )
        },

        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(

            color = Color.Black, fontSize = 20.sp
        ),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyJourneyTheme {}
}