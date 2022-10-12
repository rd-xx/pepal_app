package me.rdxx.pepal.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItems(var route: String, var icon: ImageVector, var label: String) {
    object Home : NavigationItems("home", Icons.Rounded.Home, "Home")
    object Grades : NavigationItems("grades", Icons.Rounded.List, "Grades")
    object Account : NavigationItems("account", Icons.Rounded.AccountBox, "Account")
}
