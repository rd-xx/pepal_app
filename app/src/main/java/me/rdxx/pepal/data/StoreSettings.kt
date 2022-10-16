package me.rdxx.pepal.data

import android.content.Context
import android.content.res.Configuration
import androidx.core.app.LocaleManagerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class StoreSettings(private var context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Theme")

        val DEBUG_KEY = booleanPreferencesKey("debug")
        val THEME_KEY = stringPreferencesKey("theme")
        val LOCALE_KEY = stringPreferencesKey("locale")
    }

    val getDebug: Flow<Boolean?> = context.dataStore.data.map {
        it[DEBUG_KEY] ?: false
    }
    val getTheme: Flow<String?> = context.dataStore.data.map {
        it[THEME_KEY] ?: "auto"
    }
    val getLocale: Flow<String?> = context.dataStore.data.map {
        it[LOCALE_KEY] ?: "auto"
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

    suspend fun saveLocale(newLocale: String) {
        context.dataStore.edit {
            it[LOCALE_KEY] = newLocale
        }

        val systemLocale =
            LocaleManagerCompat.getSystemLocales(context).get(0)?.country?.lowercase() ?: "en"
        val locale = when (newLocale) {
            "en" -> Locale("en")
            "fr" -> Locale("fr")
            "pt" -> Locale("pt")
            else -> Locale(systemLocale)
        }
        val config = Configuration(context.resources.configuration)

        Locale.setDefault(locale)
        config.setLocale(locale)
        context.resources.apply {
            @Suppress("DEPRECATION")
            updateConfiguration(config, displayMetrics)
        }
    }
}
