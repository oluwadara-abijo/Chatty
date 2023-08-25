package com.fueled.chatty.feature.auth.presentation.login

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.fueled.chatty.core.ui.components.CenterColumn
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.tag
import com.fueled.chatty.feature.auth.LoginTag.LoginButton
import com.fueled.chatty.feature.auth.LoginTag.PasswordField
import com.fueled.chatty.feature.auth.LoginTag.UsernameField

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
) {
    val (username, setUsername) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    Screen {
        CenterColumn {
            TextField(
                value = username,
                onValueChange = setUsername,
                modifier = Modifier.tag(UsernameField),
            )
            TextField(
                value = password,
                onValueChange = setPassword,
                modifier = Modifier.tag(PasswordField),
            )
            Button(
                onClick = onLogin,
                modifier = Modifier.tag(LoginButton),
            ) {
                Text(text = "Login")
            }
        }
    }
}
