package me.rdxx.pepal.data.api

import me.rdxx.pepal.data.api.ApiConstants.LOGIN_ENDPOINT
import retrofit2.http.Body
import retrofit2.http.POST

interface PepalApi {
    @POST(LOGIN_ENDPOINT)
    suspend fun login(@Body request: LoginRequest): String
}

data class LoginRequest(
    val username: String,
    val password: String
)