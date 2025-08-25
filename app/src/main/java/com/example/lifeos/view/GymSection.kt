package com.example.lifeos.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lifeos.R
import com.example.lifeos.model.data.Meal
import com.example.lifeos.model.viewmodel.CalorieViewModel
import java.time.LocalDate



@Composable
fun GymScreen(navController: NavHostController){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var caloriesConsumed by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    val viewModel: CalorieViewModel = viewModel()
    val meals by viewModel.meals.collectAsState(initial = emptyList())
    val totalCalories = meals.sumOf { it.calories }




    Scaffold(
        containerColor = Color(0xFF121212),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 0.08f * screenWidth.value.dp,
                        end = 0.08f * screenWidth.value.dp,
                        bottom = 10.dp
                    )
                    .background(
                        shape = RoundedCornerShape(40),
                        color = Color(0xFF4CAF50).copy(alpha = 0.75f)
                    )
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate("homepage")},
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.home_em),
                        contentDescription = "home",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF000000)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("study") {
                            popUpTo("homepage") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.study_em),
                        contentDescription = "explore",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF000000)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("gym") {
                            popUpTo("homepage") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.gym),
                        contentDescription = "cart",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF000000)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("finance") {
                            popUpTo("homepage") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.finance_em),
                        contentDescription = "account",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF000000)
                    )
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text("Today's Workout",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                )
            }
//            Spacer(modifier = Modifier.height(8.dp))
            CalendarScreen(
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Arish",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                IconButton(onClick = {
                                    // TODO: Open Edit Dialog or Screen
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Edit Info",
                                        tint = Color(0xFF4CAF50)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Gender: Male", color = Color.White)
                                Text("Age: 21", color = Color.White)
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Height: 167 cm", color = Color.White)
                                Text("Weight: 75 kg", color = Color.White)
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Text("Daily Consumption: 2000 kcal", color = Color.White)
                        }
                    }
                }
                item {
                    Row {
                        CalorieProgressBar(
                            caloriesConsumed = totalCalories,
                            calorieGoal = 2000,
                            onClick = { showDialog = true }
                        )

                        IconButton(onClick = {
                            navController.navigate("meal")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Meals",
                                tint = Color(0xFF4CAF50)
                            )
                        }
                    }

                }
            }
            if (showDialog) {
                CalorieEntryDialog(
                    onDismiss = { showDialog = false },
                    onAdd = { mealName, cal ->
                        viewModel.addMeal(Meal(name = mealName, calories = cal))
                    }
                )
            }

        }
    }
}