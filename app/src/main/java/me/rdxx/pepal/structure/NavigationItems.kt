package me.rdxx.pepal.structure

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItems(var route: String, var icon: ImageVector, var label: String) {
    object Home : NavigationItems("home", Icons.Rounded.Home, "Home")
    object Grades : NavigationItems("grades", Icons.Rounded.Grading, "Grades")
    object Account : NavigationItems("account", Icons.Rounded.AccountCircle, "Account")
    object Settings : NavigationItems("settings", Icons.Rounded.Settings, "Settings")
    object Menu : NavigationItems("menu", Icons.Rounded.MenuBook, "Menu")
}
