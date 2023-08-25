package com.fueled.chatty.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.feature.auth.presentation.login.LoginScreen

object AuthGraph : Graph("auth")

sealed class AuthDestinations {
    object Login : Destination("login")
}

fun NavGraphBuilder.addLoginScreen(
    onLogin: () -> Unit,
    graph: Graph,
) {
    composable(
        route = AuthDestinations.Login.createRoute(graph),
    ) {
        LoginScreen(onLogin)
    }
}
