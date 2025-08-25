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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.lifeos.R
import java.time.LocalDate

@Composable
fun FinanceScreen(navController: NavHostController){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }



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
                    onClick = { navController.navigate("homepage") },
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
                        painter = painterResource(R.drawable.gym_em),
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
                        painter = painterResource(R.drawable.finance),
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
                Text("Today's Budget",
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
        }
    }
}