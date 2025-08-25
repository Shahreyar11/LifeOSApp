package com.example.lifeos.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lifeos.model.remote.ApiResponse
import com.example.lifeos.model.remote.LoginRequest
import com.example.lifeos.model.remote.RetrofitClient
import com.example.lifeos.model.remote.UserData
import com.example.lifeos.model.remote.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    fun registerUser(email: String, password: String, onResult: (Boolean, String, UserData?) -> Unit) {
        val request = UserRequest(email, password)

        RetrofitClient.instance.registerUser(request)
            .enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val body = response.body()!!
                        onResult(body.status, body.message, body.data)
                    } else {
                        onResult(false, response.errorBody()?.string() ?: "Unknown error", null)
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onResult(false, t.message ?: "Network failure", null)
                }
            })
    }
    fun logoutUser(onResult: (Boolean, String) -> Unit) {
        RetrofitClient.instance.logout()
            .enqueue(object : retrofit2.Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val status = response.body()?.status ?: false
                        val message = response.body()?.message ?: "Unknown"
                        onResult(status, message)
                    } else {
                        onResult(false, "Server error")
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onResult(false, t.message ?: "Network error")
                }
            })
    }
    fun loginUser(email: String, password: String, onResult: (Boolean, String, UserData?) -> Unit) {
        val request = LoginRequest(email, password)

        RetrofitClient.instance.login(request).enqueue(object : retrofit2.Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        onResult(body.status, body.message, body.data)
                    } else {
                        onResult(false, "Empty response from server", null)
                    }
                } else {
                    onResult(false, "Login failed: ${response.code()}", null)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(false, "Error: ${t.message}", null)
            }
        })
    }

}



