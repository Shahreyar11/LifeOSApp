package com.example.lifeos.view

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.lifeos.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.time.LocalDate
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lifeos.model.viewmodel.MainViewModel
import com.example.lifeos.model.viewmodel.TasksViewModel
import com.example.lifeos.model.viewmodel.TasksViewModelFactory
import com.example.lifeos.model.viewmodel.UserViewModel


@Composable
fun HomePage(navController: NavHostController) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var habitText by remember { mutableStateOf(" ") }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val vm: MainViewModel = viewModel()
    val prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    val userId = prefs.getString("email", "") ?: ""
    val formattedDate = selectedDate.toString()
    val tasksViewModel: TasksViewModel = viewModel(
        factory = TasksViewModelFactory(context.applicationContext as Application, userId, formattedDate),
        key = "$userId-$formattedDate"
    )
    val habitList by tasksViewModel.tasks.collectAsState()



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
                    onClick = { },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.home),
                        contentDescription = "home",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF000000)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("study") {
                            popUpTo("home") { inclusive = true }
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
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.gym_em),
                        contentDescription = "cart",
                        modifier = Modifier.size(32.dp),
                        tint = Color(0xFF000000)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("finance") {
                            popUpTo("profile") { inclusive = true }
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
                Text("Today's Progress",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    )
//                Text("Hi, ${userViewModel.username}",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.W500,
//                    fontFamily = FontFamily.Monospace,
//                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)
//                )
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                        .clickable {
                            vm.logoutUser { success, message ->
                                if (success) {
                                    // Clear local SharedPreferences
                                    val prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                                    prefs.edit().clear().apply()

                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

                                    // Navigate back to login (clear back stack)
                                    navController.navigate("login") {
                                        popUpTo("homepage") { inclusive = true }
                                    }
                                } else {
                                    Toast.makeText(context, "Logout failed: $message", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                )

            }
//            Spacer(modifier = Modifier.height(8.dp))
            CalendarScreen(
                selectedDate = selectedDate,
                onDateSelected = { newDate ->
                    selectedDate = newDate
                }
            )
            OutlinedTextField(
                value = habitText,
                onValueChange = { habitText = it },
                label = { Text("Enter a Task") },
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = {
                    if (habitText.isNotBlank()) {
                        tasksViewModel.addTask(habitText)
                        habitText = ""
                    }
                },
                modifier = Modifier.align(Alignment.End).padding(3.dp)
            ) {
                Text("Add")
            }

            Spacer(modifier = Modifier.height(3.dp))

            LazyColumn {
                items(habitList) { task ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 1.dp)
                    ) {
                        Checkbox(
                            checked = task.isDone,
                            onCheckedChange = {
                                tasksViewModel.toggleTask(task)
                            }
                        )
                        Text(
                            text = task.name,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewHomePage(){
    HomePage(rememberNavController())
}