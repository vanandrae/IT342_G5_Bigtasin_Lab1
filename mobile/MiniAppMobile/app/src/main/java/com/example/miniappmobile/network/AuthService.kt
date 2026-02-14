package com.example.miniappmobile.network

import com.example.miniappmobile.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth/register")
    suspend fun register(@Body user: User): Response<Void>

    @POST("api/auth/login")
    suspend fun login(@Body user: User): Response<User>
}