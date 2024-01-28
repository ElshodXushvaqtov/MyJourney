package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.myjourney.R
import com.example.myjourney.SearchScreen
import com.example.myjourney.data.Places
import com.example.myjourney.screens.ui.theme.MyJourneyTheme
import kotlin.math.log

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
private lateinit var intent: Intent

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJourneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home() {
    Scaffold(
        bottomBar = {
            NavBar()
        }
    ) { innerPadding ->
        HomeScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier
) {
    context = LocalContext.current

    Column(
        modifier = modifier
            .padding(18.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Home Screen" }
    ) {
        Spacer(modifier = Modifier.size(5.dp))
        ToolbarHome()
        Header()
        Categories()
        Spacer(modifier = Modifier.height(15.dp))
        Recommendations()
    }
}

@Composable
private fun ToolbarHome() {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.home_profile),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                text = "Welcome Home", style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    color = Color(0xFF73848C)
                )
            )
            Text(
                text = "Elshodbek Xushvaqtov", style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp,
                    color = Color(0xFF452933)
                )
            )
        }
        Image(painter = painterResource(id = R.drawable.notification), contentDescription = null)
    }
}

@Composable
fun Header() {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    context = LocalContext.current
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.home_main),
            contentDescription = null,
            modifier = Modifier
                .size(340.dp, 285.dp)
                .align(Alignment.Center),
        )

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = {
                    context.startActivity(
                        Intent(
                            context,
                            SearchScreen::class.java
                        )
                    )
                })
                .size(300.dp, 80.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Where to go", style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W400,
                    fontSize = 24.sp,
                    color = Color(0x8073848C)
                )
            )
            Spacer(modifier = Modifier.weight(1F))
            Image(painter = painterResource(id = R.drawable.search), contentDescription = null)
        }
    }
}

@Composable
private fun Categories() {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Spacer(modifier = Modifier.size(24.dp))
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = "Popular Categories", style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W600,
                fontSize = 18.sp,
                color = Color(0xFF452933)
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Category(paint = R.drawable.cat_1, text = "Mountain")
            Category(paint = R.drawable.cat_2, text = "Adventure")
            Category(paint = R.drawable.cat_3, text = "Beach")
            Category(paint = R.drawable.cat_4, text = "City")
        }
    }
}

@Composable
fun Category(@DrawableRes paint: Int, text: String) {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    context = LocalContext.current
    Column(
        modifier = Modifier.defaultMinSize(minWidth = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {},
            modifier = Modifier.size(64.dp)
        ) {
            Image(
                painter = painterResource(id = paint),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text, style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                color = Color(0xFF73848C)
            )
        )
    }
}

@Composable
private fun NavBar() {
    context = LocalContext.current
    Modifier
        .padding(end = 20.dp)
        .fillMaxWidth()
        .defaultMinSize(minWidth = 327.dp, minHeight = 67.dp)
        .border(shape = RoundedCornerShape(12.dp), width = 1.dp, color = Color.Transparent)
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .defaultMinSize(minWidth = 370.dp, minHeight = 67.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            IconButton(onClick = {}, content = {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                )
            },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(40.dp)
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            IconButton(
                onClick = { context.startActivity(Intent(context, DetailsScreen::class.java)) },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ticket),
                        contentDescription = null,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                    )
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(40.dp)
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.size(90.dp))

            IconButton(onClick = {}, content = {
                Icon(
                    painter = painterResource(id = R.drawable.saved),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                )
            },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(40.dp)
                    .height(40.dp)
            )

            Spacer(modifier = Modifier.size(12.dp))
            IconButton(onClick = {}, content = {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .width(30.dp)
                        .height(30.dp)
                )
            },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(40.dp)
                    .height(40.dp)
            )
        }
        IconButton(
            onClick = { context.startActivity(Intent(context, SearchScreen::class.java)) },
            content = {
                Image(
                    painter = painterResource(id = R.drawable.search_bar),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(50.dp)
                        .height(50.dp)
                )
            },
            modifier = Modifier
                .align(Alignment.Center)
                .width(50.dp)
                .height(50.dp)
                .offset(y = (-20).dp)
        )
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun Recommendations() {
    val places = remember {
        addPlaces()
    }
    LazyRow {
        items(places) {
            RecommendationsItem(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationsItem(places: Places) {
    context = LocalContext.current
    intent = Intent(context, DetailsScreen::class.java)

    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                intent.putExtra("placeImg", places.img)
                intent.putExtra("placeName", places.name)
                intent.putExtra("placeDescription", places.description)
                Log.d("BBB", places.name + "\n${places.description}")
                context.startActivity(intent)
            }
        ) {
            Image(
                painter = painterResource(id = places.img),
                contentDescription = null,
                modifier = Modifier.size(width = 250.dp, height = 250.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = places.name)
    }
}

fun addPlaces(): MutableList<Places> {
    val placeList: MutableList<Places> = mutableListOf()
    placeList.add(
        Places(
            "Hazrati Imom Majmuasi",
            R.drawable.hazrati_imom,
            "Hazrati Imom majmuasi 16303Toshkentning diniy yodgorliklaridan biri bu aholi orasida Xast-Imom nomi bilan mashhur Hazrati Imom ansamblidir. Maydon eski shaharning orqasida joylashgan bo‘lib, u 1966 yildagi kuchli zilziladan omon qolgan." +
                    "\n Majmua olim va din arbobi, birinchi toshkentlik imom Kaffol ash Shoshiy dafn etilgan joyda qurilgan."
        )
    )
    placeList.add(
        Places(
            "Imom Al-Buxoriy Maqbarasi",
            R.drawable.al_buxoriy,
            "Musulmon olamining taniqli muhaddislaridan biri Imom al-Buxoriy 810 yil 21-iyulda Buxoroda tavallud topgan, 870 yilda Samarqanddan 25 km uzoqda joylashgan Xartang qishlog'ida (Samarqand viloyatining hozirgi Chelak tumani) vafot etgan va o sha yerda dafn etilgan.\n" +
                    " Biroq, bu joy asrlar davomida qarovsiz holatda qolgan edi."
        )
    )
    placeList.add(
        Places(
            "Oq Saroy",
            R.drawable.oq_saroy,
            "Oq saroy, Shahrisabzning asosiy diqqatga sazovor joyi va marvarididir." +
                    "\nOy nurida saroy fasadi va gumbazlari ranglari o'zgarib turishi sababli, Oqsaroy o‘zining shunday afsonaviy nomini olgan."
        )
    )
    placeList.add(
        Places(
            "Ichan Qal'a",
            R.drawable.ichan_qala,
            "Ichan Qal’a 4735O‘tmishga tashrif buyurishni xohlaysizmi? Eski ko‘chalarni aylanib o‘tib, haqiqiy tarixiy shaharni ko‘rishni istaysizmi? Bu orzuni haqiqatga aylantirish mumkin, siz ko‘zingiz bilan haqiqiy sharq ertagini ko‘rish  uchun\n" +
                    " Xivaga – ko‘plab madrasalar, masjidlar, minoralar,  hunarmandchilik ustaxonalari va mehmonxonalari bilan  ochiq-osmon ostidagi  O‘zbekistonning javohiri bo‘lgan Ichan-Qal’a shahriga kelishingiz kerak."
        )
    )
    return placeList
}

@Preview(showBackground = true)
@Composable
fun HomeView() {
    MyJourneyTheme {
        Home()
    }
}