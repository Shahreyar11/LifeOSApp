package com.example.lifeos.view

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current

    // Read token from SharedPreferences
    val prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    val token = prefs.getString("token", null)
    val userId = prefs.getString("userId", null)


    // Navigate after delay
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1500)

        if (token != null) {
            navController.navigate("homepage") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("landing") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    // Splash UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Life OS 🚀", style = MaterialTheme.typography.headlineLarge)
    }
}
