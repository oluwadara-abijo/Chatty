package com.fueled.chatty.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        ProjectDarkColors
    } else {
        ProjectLightColors
    }

    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.run {
            setSystemBarsColor(
                color = colors.background,
            )
            setNavigationBarColor(
                color = colors.surface,
            )
        }
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = Typography,
        shapes = ProjectShapes,
    )
}
