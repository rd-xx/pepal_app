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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import me.rdxx.pepal.R
import me.rdxx.pepal.data.StoreSettings
import me.rdxx.pepal.structure.SettingsData


@Composable
fun SettingsScreen() {
    val dataStore = StoreSettings(LocalContext.current)
    val scope = rememberCoroutineScope()

    var storedDebug = dataStore.getDebug.collectAsState(initial = false).value!!
    val storedTheme = dataStore.getTheme.collectAsState(initial = "auto").value!!
    val storedLocale = dataStore.getLocale.collectAsState(initial = "auto").value!!

    val elements = listOf(
        SettingsData(stringResource(R.string.debug_mode)) {
            Switch(
                checked = storedDebug,
                onCheckedChange = {
                    storedDebug = it
                    scope.launch {
                        dataStore.saveDebug(it)
                    }
                })
        },
        SettingsData(stringResource(R.string.theme)) {
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
                    "auto" -> stringResource(
                        R.string.change_to,
                        stringResource(R.string.light).lowercase()
                    )
                    "light" -> stringResource(
                        R.string.change_to,
                        stringResource(R.string.dark).lowercase()
                    )
                    "dark" -> stringResource(
                        R.string.change_to,
                        stringResource(R.string.auto_short).lowercase()
                    )
                    else -> stringResource(
                        R.string.change_to,
                        stringResource(R.string.auto_short).lowercase()
                    )
                }
                Text(text = nextTheme)
            }
        },
        SettingsData(stringResource(R.string.locale)) {
            Button(onClick = {
                scope.launch {
                    dataStore.saveLocale(
                        when (storedLocale) {
                            "auto" -> "en"
                            "en" -> "fr"
                            "fr" -> "pt"
                            "pt" -> "auto"
                            else -> "auto"
                        }
                    )
                }
            }) {
                val nextLocale = when (storedLocale) {
                    "auto" -> stringResource(R.string.change_to, stringResource(R.string.english))
                    "en" -> stringResource(R.string.change_to, stringResource(R.string.french))
                    "fr" -> stringResource(R.string.change_to, stringResource(R.string.portuguese))
                    "pt" -> stringResource(R.string.change_to, stringResource(R.string.auto_short))
                    else -> stringResource(R.string.change_to, stringResource(R.string.auto_short))
                }
                Text(text = nextLocale)
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
            fontWeight = W500,
            text = stringResource(R.string.settings)
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
