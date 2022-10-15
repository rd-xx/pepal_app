package me.rdxx.pepal.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import me.rdxx.pepal.data.StoreSettings
import me.rdxx.pepal.utils.SettingsData


@Composable
fun SettingsScreen() {
    val dataStore = StoreSettings(LocalContext.current)
    val scope = rememberCoroutineScope()

    var storedDebug = dataStore.getDebug.collectAsState(initial = false).value!!
    val storedTheme = dataStore.getTheme.collectAsState(initial = "auto").value!!

    val elements = listOf(
        SettingsData("Debug mode") {
            Switch(
                checked = storedDebug,
                onCheckedChange = {
                    storedDebug = it
                    scope.launch {
                        dataStore.saveDebug(it)
                    }
                })
        },
        SettingsData("Theme") {
            Button(onClick = {
                scope.launch {
                    dataStore.saveTheme(
                        when (storedTheme) {
                            "auto" -> "light"
                            "light" -> "dark"
                            "dark" -> "auto"
                            else -> "auto"
                        }
                    )
                }
            }) {
                val nextTheme = when (storedTheme) {
                    "auto" -> "Change to light"
                    "light" -> "Change to dark"
                    "dark" -> "Change to auto"
                    else -> "Change to auto"
                }
                Text(text = nextTheme)
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .background(color = Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(0.dp, 32.dp, 0.dp, 16.dp),
//                .background(Color.Blue),
            fontSize = 24.sp,
            text = "Settings"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(.8f),
//                .background(Color.Green),
        ) {
            elements.forEachIndexed { index, element ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp),
//                        .background(Color.White),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = element.text)
                    element.content()
                }

                if (index != elements.size - 1) {
                    Divider(thickness = 0.5.dp)
                }
            }
        }
    }
}