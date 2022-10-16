package me.rdxx.pepal.structure

import androidx.compose.runtime.Composable

data class SettingsData(var text: String, var content: @Composable () -> Unit)
