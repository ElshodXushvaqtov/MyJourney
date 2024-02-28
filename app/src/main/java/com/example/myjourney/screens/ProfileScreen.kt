package com.example.myjourney.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myjourney.R
import com.example.myjourney.ui.theme.MyJourneyTheme

class ProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var checked by remember { mutableStateOf(true) }
                    var openDialog = remember { mutableStateOf(false) }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = "Your Profile",
                            fontWeight = FontWeight.Black,
                            fontSize = 32.sp
                        )
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(90.dp),
                                model = "https://images.ctfassets.net/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=1200&h=992&q=70&fm=webp",
                                contentDescription = null
                            )
                            Column {
                                Text(
                                    modifier = Modifier.padding(15.dp),
                                    text = "Hello, Nigga",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp
                                )
                                Text(text = "Tashkent, Uzbekistan", color = Color.Gray)
                            }
                        }
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Text(
                                modifier = Modifier.padding(15.dp),
                                text = "Personal Information",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                            )
                            Icon(
                                modifier = Modifier.align(Alignment.End),
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = null
                            )
                        }
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Text(
                                modifier = Modifier.padding(15.dp),
                                text = "Notification",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Icon(
                                modifier = Modifier.align(Alignment.End),
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = null
                            )

                        }
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Text(
                                modifier = Modifier.padding(15.dp),
                                text = "FAQ",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Icon(
                                modifier = Modifier.align(Alignment.End),
                                imageVector = Icons.Outlined.Email,
                                contentDescription = null
                            )

                        }
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Text(
                                modifier = Modifier.padding(15.dp),
                                text = "Dark Mode",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Switch(checked = checked, onCheckedChange = { checked = it })
                        }
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Text(
                                modifier = Modifier.padding(15.dp),
                                text = "Language",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Icon(
                                modifier = Modifier.align(Alignment.End),
                                painter = painterResource(id = R.drawable.baseline_language_24),
                                contentDescription = null
                            )

                        }
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .clickable {
                                    openDialog.value = true
                                },
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { },
                                text = "Logout",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Icon(
                                modifier = Modifier.align(Alignment.End),
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = null
                            )

                        }

                        if (openDialog.value) {
                            AlertDialog(
                                title = {
                                    Text(
                                        text = "Logout",
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 32.sp
                                    )
                                },
                                onDismissRequest = { true },
                                confirmButton = {
                                    TextButton(onClick = { openDialog.value = false })
                                    { Text(text = "Logout", color = Color.Black) }
                                },
                                dismissButton = {
                                    TextButton(onClick = { openDialog.value = false })
                                    {
                                        Text(
                                            text = "Cancel",
                                            Modifier.background(Color(255, 235, 59, 255)),
                                            color = Color.Black
                                        )
                                    }
                                },
                                text = {
                                    Box {
                                        Text(text = "Do you really want to logout from this account")
                                    }
                                }
                            )
                        }


                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    MyJourneyTheme {}
}