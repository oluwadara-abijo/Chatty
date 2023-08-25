package com.fueled.chatty.feature.auth

import com.fueled.chatty.core.ui.util.UiTag

sealed class LoginTag(override val key: String) : UiTag {
    object LoginButton : LoginTag("auto_login")
    object UsernameField : LoginTag("auto_username")
    object PasswordField : LoginTag("auto_password")
}
