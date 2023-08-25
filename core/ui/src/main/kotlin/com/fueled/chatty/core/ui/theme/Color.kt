package com.fueled.chatty.core.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val TextColor = Color(color = 0xFF2B292C)
val Primary = Color(color = 0xFFD43C47)
val PrimaryVariant = Color(color = 0xFFFD9199)

val ProjectLightColors = lightColors(
    primary = Primary,
    onPrimary = Color.White,
    primaryVariant = PrimaryVariant,
    secondary = TextColor,
    secondaryVariant = TextColor,
    onSecondary = Color.White,
    onBackground = TextColor,
    onSurface = TextColor,
)

val ProjectDarkColors = darkColors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    onPrimary = Color.White,
    onSecondary = Color.White,
)
