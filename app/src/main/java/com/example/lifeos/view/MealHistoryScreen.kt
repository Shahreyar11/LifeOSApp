package com.example.lifeos.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lifeos.model.viewmodel.CalorieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealHistoryScreen(navController: NavHostController, viewModel: CalorieViewModel = viewModel()) {
    val meals by viewModel.meals.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meal History") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ) {
            items(meals) { meal ->
                Text(text = "${meal.name}: ${meal.calories} cal", modifier = Modifier.padding(8.dp))
            }
        }
    }
}