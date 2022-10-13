package me.rdxx.pepal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    val items = listOf(MenuItems.Login)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            FilledTonalButton(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Text(text = item.label)
                },
                onClick = { navController.navigate(item.route) },
            )
        }
    }
}

sealed class MenuItems(var route: String, var label: String) {
    object Login : MenuItems("login", "Login")
}
