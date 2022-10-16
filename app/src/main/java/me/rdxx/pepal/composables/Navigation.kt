package me.rdxx.pepal.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.rdxx.pepal.screens.*
import me.rdxx.pepal.structure.NavigationItems

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItems.Home.route) {
        composable(NavigationItems.Home.route) {
            HomeScreen()
        }
        composable(NavigationItems.Grades.route) {
            GradesScreen()
        }
        composable(NavigationItems.Account.route) {
            AccountScreen()
        }
        composable(NavigationItems.Settings.route) {
            SettingsScreen()
        }
        composable(NavigationItems.Menu.route) {
            MenuScreen(navController)
        }
        composable("login") {
            LoginScreen()
        }
    }

}
