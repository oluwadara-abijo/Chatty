package com.fueled.chatty.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

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
                color = Color.Transparent,
                darkIcons = useDarkIcons,
            )
            setNavigationBarColor(
                color = Color.Black,
                darkIcons = useDarkIcons,
            )
        }
    }

    MaterialTheme(
        colors = colors,
        typography = ProjectTypography,
        shapes = ProjectShapes,
        content = content,
    )
}
