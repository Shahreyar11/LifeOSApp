package com.example.lifeos.view


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lifeos.model.viewmodel.MainViewModel

@Composable
fun LoginSignupScreen(navController: NavHostController, vm: MainViewModel = viewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        color = Color(0xFF121212)
    ) {
        Scaffold(
            containerColor = Color(0xFF121212),
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    // App Title
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "LifeOS",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Join LifeOS to start your habit journey!",
                            fontSize = 16.sp,
                            color = Color(0xFFAAAAAA),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp),
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Form
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),

                            modifier = Modifier.fillMaxWidth()
                        )

                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),

                                    modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Submit Button
                    Button(
                        onClick = {
                            vm.registerUser(email, password) { status, message, data ->
                                if (status) {
                                    val prefs = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

                                    // save token + email safely
                                    prefs.edit()
                                        .putString("email", data?.email ?: "")
                                        .putString("token", data?.token ?: "")
                                        .apply()

                                    // navigate to homepage after saving
                                    navController.navigate("homepage")
                                } else {
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Register", fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(){
                        Text(text = "Already Registered ?", color = Color.White, fontSize = 12.sp, style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Login", color = Color.Yellow, fontSize = 12.sp, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.clickable(onClick = {
                            navController.navigate("login")
                        }))
                    }


                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginSignupScreen() {
    LoginSignupScreen(rememberNavController())
}
