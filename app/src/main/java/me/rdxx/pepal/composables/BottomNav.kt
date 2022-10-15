package me.rdxx.pepal.composables

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import me.rdxx.pepal.data.StoreSettings
import me.rdxx.pepal.utils.NavigationItems

@Composable
fun BottomNavigationBar(navController: NavController) {
    val dataStore = StoreSettings(LocalContext.current)
    val debug = dataStore.getDebug.collectAsState(initial = false).value!!
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Grades,
        NavigationItems.Account,
        NavigationItems.Settings,
        NavigationItems.Menu
    )

    BottomAppBar {
        items.forEach { item ->
            if (item.route == "menu" && !debug) return@forEach
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                    )
                },
                label = { item.label },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                modifier = Modifier
                    .padding(1.dp)
                    .offset(y = 5.dp)
            )
        }
    }
}
