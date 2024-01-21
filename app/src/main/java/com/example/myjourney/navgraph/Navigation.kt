package com.example.myjourney.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myjourney.screens.Splash
import com.example.myjourney.screens.Welcome

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            Splash()
        }
        composable(Screen.Welcome.route) {
            Welcome()
        }
        composable(Screen.Home.route) {

        }
        composable(Screen.Detail.route) {

        }


    }

}