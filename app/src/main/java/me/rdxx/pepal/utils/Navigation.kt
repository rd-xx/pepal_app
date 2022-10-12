package me.rdxx.pepal.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.rdxx.pepal.screens.AccountScreen
import me.rdxx.pepal.screens.GradesScreen
import me.rdxx.pepal.screens.HomeScreen

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
    }
}
