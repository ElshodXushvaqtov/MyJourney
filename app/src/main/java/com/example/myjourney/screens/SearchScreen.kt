package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Search()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Search() {

    context = LocalContext.current
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    var history = remember {
        mutableStateListOf("")
    }
    Scaffold {
        SearchBar(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                history.add(text)
                active = false
                text = ""
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon"
                    )
                }
            }
        ) {
LazyColumn(content = )
            history.forEach {
                Row(modifier = Modifier.padding(all = 10.dp)) {
                    if (it != "") {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Default.History,
                            contentDescription = null
                        )
                        Text(text = it)
                    }
                }
            }

        }
    }
}

object Places {
    var places = mutableListOf(
        "Hazrati Imom Majmuasi",
        "Imom Al-Buxoriy Maqbarasi",
        "Oq Saroy",
        "Ichan Qal'a",
        "Bildirsoy tog‘-chang‘i kurorti",
    )

    fun search(text: String): MutableList<String> {
        places.filter {
            it.startsWith(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyJourneyTheme {}
}