package com.example.lifeos.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalorieProgressBar(
    caloriesConsumed: Int,
    calorieGoal: Int,
    onClick: () -> Unit
) {
    val progress = caloriesConsumed.toFloat() / calorieGoal

    Box(
        modifier = Modifier
            .size(180.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = progress.coerceIn(0f, 1f),
            color = Color.Red,
            strokeWidth = 12.dp,
            modifier = Modifier.fillMaxSize()
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$caloriesConsumed / $calorieGoal kcal",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "Tap to add meal",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
