package com.example.lifeos.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("landing") {
            HomeScreen(navController) // your screen with Login/Register options
        }
        composable("register") {
            LoginSignupScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("homepage") {
            HomePage(navController)
        }
        composable("study") {
            StudyScreen(navController)
        }
        composable("gym") {
            GymScreen(navController)
        }
        composable("finance") {
            FinanceScreen(navController)
        }
        composable("meal") {
            MealHistoryScreen(navController)
        }
    }
}
