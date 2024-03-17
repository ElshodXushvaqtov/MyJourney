package com.example.myjourney.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import coil.compose.AsyncImage
import com.example.myjourney.R
import com.example.myjourney.data.Places
import com.example.myjourney.screens.ui.theme.MyJourneyTheme
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("StaticFieldLeak")
private lateinit var context: Context
private lateinit var intent: Intent
private lateinit var auth: FirebaseAuth

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
    auth = FirebaseAuth.getInstance()
    context = LocalContext.current
    val profileName = auth.currentUser?.displayName
    val profileImg = auth.currentUser?.photoUrl
    Log.d("uName", profileName.toString())
    Column(
        modifier = modifier
            .padding(18.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Home Screen" }
    ) {
        Spacer(modifier = Modifier.size(5.dp))
        if (profileName != null && profileImg != null) {
            ToolbarHome(profileName, profileImg)
        }
        Header()
        Categories()
        Spacer(modifier = Modifier.height(15.dp))
        Recommendations()
    }
}

@Composable
private fun ToolbarHome(uName: String, uImg: Uri?) {
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = uImg,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                text = "Assalomu aleykum", style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W400,
                    fontSize = 13.sp,
                    color = Color(0xFF73848C)
                )
            )
            Text(
                text = uName, style = TextStyle(
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
                text = "Manzilni qidirish", style = TextStyle(
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
            text = "Mashhur Manzillar", style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W600,
                fontSize = 18.sp,
                color = Color(0xFF452933)
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Category(
                R.drawable.madaniy_c,
                text = "Madaniy"
            )
            Category(
                paint = R.drawable.badiiy_c,
                text = "Badiiy"
            )
            Category(
                paint = R.drawable.ziyorat_c,
                text = "Ziyorat"
            )
            Category(
                paint = R.drawable.tabiat_c,
                text = "Tabiat"
            )
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
            modifier = Modifier.size(width = 80.dp, height = 65.dp)
        ) {
            Image(painter = painterResource(id = paint), contentDescription = null)
        }

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text, style = TextStyle(
                fontFamily = gilroy,
                fontWeight = FontWeight.W400,
                fontSize = 13.sp,
                color = Color(0xFF73848C)
            )
        )
    }
}

@Composable
private fun NavBar() {
    context = LocalContext.current
    auth = FirebaseAuth.getInstance()
    val intent = Intent(context, ProfileScreen::class.java)
    val profileName = auth.currentUser?.displayName
    val profileImg = auth.currentUser?.photoUrl
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
            IconButton(
                onClick = { context.startActivity(Intent(context, HomeScreen::class.java)) },
                content = {
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
                onClick = {},
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
            IconButton(
                onClick = {
                    context.startActivity(
                        intent
                    )
                    Log.d("HomeProfile", profileName.toString()+"\n"+profileImg.toString())
                    intent.putExtra("profileImg", profileImg.toString())
                    intent.putExtra("profileName", profileName.toString())
                },
                content = {
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
    val gilroy = FontFamily(
        Font(R.font.gilroy, FontWeight.Normal),
        Font(R.font.gilroy_bold, FontWeight.Bold)
    )
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
                intent.putExtra("moreImages", places.moreImages)
                Log.d("BBB", places.name + "\n${places.description}")
                context.startActivity(intent)
            }
        ) {
            AsyncImage(
                model = places.img,
                contentDescription = null,
                modifier = Modifier.size(width = 250.dp, height = 250.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        places.name?.let {
            Text(
                text = it, style = TextStyle(
                    fontFamily = gilroy,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    color = Color(0xFF452933)
                )
            )
        }
    }
}

fun addPlaces(): MutableList<Places> {
    val placeList: MutableList<Places> = mutableListOf()
    placeList.add(
        Places(
            "Hazrati Imom Majmuasi",
            "https://meros.uz/upload/2017/02/9da5ba327cc8ee44e63f6d63bd112aa3-large.jpg",
            "Hazrati Imom majmuasi 16303Toshkentning diniy yodgorliklaridan biri bu aholi orasida Xast-Imom nomi bilan mashhur Hazrati Imom ansamblidir. Maydon eski shaharning orqasida joylashgan bo‘lib, u 1966 yildagi kuchli zilziladan omon qolgan." +
                    "\n Majmua olim va din arbobi, birinchi toshkentlik imom Kaffol ash Shoshiy dafn etilgan joyda qurilgan.",
            "Ziyorat",
            arrayOf(
                "https://meros.uz/upload/2017/02/9da5ba327cc8ee44e63f6d63bd112aa3-large.jpg",
                "https://storage.kun.uz/source/1/xL4YxoXhSxhu5hIQDkqDa6H5k5mNvrLu.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/3/34/Barakhan_Madrasah_Tashkent.jpg",
                "https://img1.advisor.travel/1314x680px-Hazrati_Imom_majmuasi_5.jpg"
            )
        )
    )
    placeList.add(
        Places(
            "Imom Al-Buxoriy Maqbarasi",
            "https://mirumarasadov.files.wordpress.com/2014/10/0_1853f_af66a20c_l.jpg",
            "Musulmon olamining taniqli muhaddislaridan biri Imom al-Buxoriy 810 yil 21-iyulda Buxoroda tavallud topgan, 870 yilda Samarqanddan 25 km uzoqda joylashgan Xartang qishlog'ida (Samarqand viloyatining hozirgi Chelak tumani) vafot etgan va o sha yerda dafn etilgan.\n" +
                    " Biroq, bu joy asrlar davomida qarovsiz holatda qolgan edi.",
            "Ziyorat",
            arrayOf(
                "https://mirumarasadov.files.wordpress.com/2014/10/0_1853f_af66a20c_l.jpg",
                "https://ilmlar.uz/wp-content/uploads/2022/12/Samarqanddagi-Imom-Al-Buxoriy.jpg",
                "https://telegra.ph/file/b24129272d4c564e811e3.jpg",
                "https://oyina.uz/storage/generations/September2023/6k20K4oLj8PvlxV5aIpU.jpg"
            )
        )
    )
    placeList.add(
        Places(
            "Oq Saroy",
            "https://www.gazeta.uz/media/img/2016/03/ENrM7H14592159353382_l.jpg",
            "Oq saroy, Shahrisabzning asosiy diqqatga sazovor joyi va marvarididir." +
                    "\nOy nurida saroy fasadi va gumbazlari ranglari o'zgarib turishi sababli, Oqsaroy o‘zining shunday afsonaviy nomini olgan.",
            "Madaniy",
            arrayOf(
                "https://www.gazeta.uz/media/img/2016/03/ENrM7H14592159353382_l.jpg",
                "https://repost.uz/storage/uploads/0-1696926435-Oleg-post-material.jpeg",
                "https://ru.travelornament.com/thumb/2/VJt0undFyeACW10N-pcoJA/580r450/d/0__7f2eb__d28d1ec__XXL.jpg",
                "https://fastly.4sqi.net/img/general/600x600/1155361_yJhQSiEmUiwEWkxqSO5Szovhb250OkUVnhmB93svcGE.jpg"
            )
        )
    )
    placeList.add(
        Places(
            "Ichan Qal'a",
            "https://uzbek-travel.com/images/uz/Landmarks/Khiva/Ichan_Kala/3669198198_67b80fb777_b.jpg",
            "Ichan Qal’a 4735O‘tmishga tashrif buyurishni xohlaysizmi? Eski ko‘chalarni aylanib o‘tib, haqiqiy tarixiy shaharni ko‘rishni istaysizmi? Bu orzuni haqiqatga aylantirish mumkin, siz ko‘zingiz bilan haqiqiy sharq ertagini ko‘rish  uchun\n" +
                    " Xivaga – ko‘plab madrasalar, masjidlar, minoralar,  hunarmandchilik ustaxonalari va mehmonxonalari bilan  ochiq-osmon ostidagi  O‘zbekistonning javohiri bo‘lgan Ichan-Qal’a shahriga kelishingiz kerak.",
            "Madaniy",
            arrayOf(
                "https://uzbek-travel.com/images/uz/Landmarks/Khiva/Ichan_Kala/3669198198_67b80fb777_b.jpg",
                "https://static.xabar.uz/crop/4/2/720_460_95_4238165090.jpg",
                "https://as2.ftcdn.net/v2/jpg/04/74/47/39/1000_F_474473960_Edi94tfr6DSqZ3ouVJCwyJgTnHKjjaOH.jpg",
                "https://uzbek-travel.com/images/uz/Landmarks/Khiva/Ichan_Kala/3633066605_8133f38f4a_b.jpg"
            )
        )
    )
    placeList.add(
        Places(
            "Bildirsoy tog‘-chang‘i kurorti",
            "https://daryo.uz/cache/2016/02/57b36d7e736525199bc4e8b45d4c65d8_L-650x433.jpg",
            "Bildirsoy tog‘-chang‘i kurortidagi qor qoplamasi yevropadagi qordan farq qiladi. Kontinental iqlim, bir tomondan Himolay tog‘lari , ikkinchi tomondan Sibir haddan tashqari haroratni va kuchli qor yog‘ishini taʼminlaydi." +
                    "\nBu yerdagi qor eng zo‘r, quruq va sovuq, chang‘i uchish uchun juda yaxshi hamda mutaxassislarning fikriga ko‘ra dunyodagi eng ajoyib qorlardan biri hisoblanadi.",
            "Tabiat",
            arrayOf(
                "https://daryo.uz/cache/2016/02/57b36d7e736525199bc4e8b45d4c65d8_L-650x433.jpg",
                "https://storage.kun.uz/source/5/nH62snH70K_HLk4oVhFsxcW0gGjOMnkO.jpg",
                "https://www.amirsoy.com/img/services/snowshoeing.jpg",
                "https://www.gazeta.uz/media/img/2021/12/lDohMO16406884622641_l.jpg"
            )
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