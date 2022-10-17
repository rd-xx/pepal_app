package me.rdxx.pepal.data.repositories

import me.rdxx.pepal.data.api.LoginRequest
import me.rdxx.pepal.data.api.PepalApi
import javax.inject.Inject

class PepalRepo @Inject constructor(private val pepalApi: PepalApi) {
    suspend fun login(username: String, password: String): String {
        val response = pepalApi.login(LoginRequest(username, password))

        println(response)
        return ""
    }
}