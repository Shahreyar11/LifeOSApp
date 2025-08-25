package com.example.lifeos.model.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/addUser")
//    @Body request → sends JSON like { "email": "abc", "password": "xyz" } to backend.
//    Call<ApiResponse> → we expect a response containing a message field.
    fun registerUser(@Body request: UserRequest): Call<ApiResponse>

    @POST("/logout")
    fun logout(): Call<ApiResponse>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<ApiResponse>

    @GET("/tasks")
    fun getTasks(): Call<ApiResponse>

}