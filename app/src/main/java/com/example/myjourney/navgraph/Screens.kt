package com.example.myjourney.navgraph

sealed class Screen(val name: String, val route: String) {
    data object Splash : Screen("splash", "splash")
    data object Welcome : Screen("welcome", "welcome")
    data object Home : Screen("home", "home")
    data object Detail : Screen("detail", "detail")
}
