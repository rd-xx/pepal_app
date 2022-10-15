package me.rdxx.pepal.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreSettings(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Theme")

        val DEBUG_KEY = booleanPreferencesKey("debug")
        val THEME_KEY = stringPreferencesKey("theme")
    }

    val getDebug: Flow<Boolean?> = context.dataStore.data.map {
        it[DEBUG_KEY] ?: false
    }
    val getTheme: Flow<String?> = context.dataStore.data.map {
        it[THEME_KEY] ?: "auto"
    }

    suspend fun saveDebug(newDebug: Boolean) {
        context.dataStore.edit {
            it[DEBUG_KEY] = newDebug
        }
    }

    suspend fun saveTheme(newTheme: String) {
        context.dataStore.edit {
            it[THEME_KEY] = newTheme
        }
    }
}
