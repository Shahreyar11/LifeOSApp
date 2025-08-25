package com.example.lifeos.model.remote

data class ApiResponse(
    val status: Boolean,
    val message: String,
    val data: UserData? // Nullable because sometimes API may not send user data
)

data class UserData(
    val email: String,
    val userId: Int? = null, // Optional if API doesn't always return it
    val token: String
)
