package com.example.prurbaproyecto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val identificador: String,
    val contrasena: String
)

data class LoginResponse(
    val mensaje: String,
    val access_token: String?
)

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}