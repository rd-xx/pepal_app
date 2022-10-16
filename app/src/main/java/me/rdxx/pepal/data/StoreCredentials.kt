package me.rdxx.pepal.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreCredentials(private var context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Theme")

        val USERNAME_KEY = stringPreferencesKey("username")
        val PASSWORD_KEY = stringPreferencesKey("password")
        val COOKIE_KEY = stringPreferencesKey("cookie")
    }

    val getUsername: Flow<String?> = context.dataStore.data.map {
        it[USERNAME_KEY] ?: ""
    }
    val getPassword: Flow<String?> = context.dataStore.data.map {
        it[PASSWORD_KEY] ?: ""
    }
    val getCookie: Flow<String?> = context.dataStore.data.map {
        it[COOKIE_KEY] ?: ""
    }

    suspend fun saveCredentials(newUsername: String, newPassword: String) {
        context.dataStore.edit {
            it[USERNAME_KEY] = newUsername
            it[PASSWORD_KEY] = newPassword
        }
    }

    suspend fun saveCookie(newCookie: String) {
        context.dataStore.edit {
            it[COOKIE_KEY] = newCookie
        }
    }
}
