package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
//                    val places: ArrayList<Places> = intent.getParcelableArrayListExtra("places")!!
                    val list = listOf(
                        "Java",
                        "Kotlin",
                        "Python",
                        "Swift",
                        "Java-script",
                        "C",
                        "C++",
                        "XML",
                        "Dart",
                        "Go",
                        "R",
                        "PHP",
                        "Ruby",
                        "Perl",
                        "SQL",
                        "Objective-C",
                        "HTML",
                        "CSS"
                    )
                    MyApp(list = list)
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier, list: List<String>) {
    Column(modifier.fillMaxSize()) {

        val textState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        SearchView(state = textState, placeHolder = "Search here...", modifier = modifier)

        val searchedText = textState.value.text

        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(items = list.filter {
                it.contains(searchedText, ignoreCase = true)
            }, key = { it }) { item ->
                ColumnItem(item = item)
            }
        }
    }
}


@Composable
fun ColumnItem(item: String) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = item, modifier = Modifier.padding(vertical = 10.dp), fontSize = 22.sp)
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue>, placeHolder: String, modifier: Modifier
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(30.dp)),
        placeholder = {
            Text(text = placeHolder)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White
        ),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(
            color = Color.Black, fontSize = 20.sp
        )
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyJourneyTheme {}
}