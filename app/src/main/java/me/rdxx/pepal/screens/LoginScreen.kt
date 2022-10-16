package me.rdxx.pepal.screens

import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import me.rdxx.pepal.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var openDialog by rememberSaveable { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
//            .background(color = Color.Red)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
//                .background(Color.Green)
                .fillMaxWidth(.7f)
        ) {
            Image(
                modifier = Modifier
//                    .background(color = Color.White)
                    .fillMaxWidth()
                    .height(40.dp),
                painter = painterResource(id = R.drawable.logo_full),
                contentDescription = "Logo",
            )

            TextField(
                modifier = Modifier
                    .padding(0.dp, 64.dp, 0.dp, 24.dp)
                    .fillMaxWidth()
                    .then(Modifier.onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            focusManager.moveFocus(FocusDirection.Down)
                            true
                        } else {
                            false
                        }
                    }),
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Nom d'utilisateur") },
                placeholder = { Text(text = "etu.brahimgou") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            focusManager.clearFocus()
                        }
                        true
                    },
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Mot de passe") },
                placeholder = { Text(text = "Mot de passe") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Row(
                modifier = Modifier
//                    .background(Color.Blue)
                    .padding(0.dp, 32.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ClickableText(
                    text = AnnotatedString("Mot de passe oublié ?"),
                    // make text small and white
                    style = MaterialTheme.typography.bodySmall.plus(TextStyle(color = MaterialTheme.colorScheme.primary)),
                    onClick = {
                        openDialog = true
                    }
                )

                Button(
                    onClick = { /*TODO*/ },
                    enabled = username.isNotEmpty() && password.isNotEmpty(),
                ) {
                    Text(text = "Se connecter")
                }
            }
        }
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                }) { Text(text = "OK") }
            },
            title = { Text(text = "Mot de passe oublié") },
            text = {
                Text(text = "Pour réinitialiser votre mot de passe, veuillez consulter directement le site de Pepal (https://pepal.eu/) et y entamer les procédures nécessaires.")
            }
        )
    }
}
