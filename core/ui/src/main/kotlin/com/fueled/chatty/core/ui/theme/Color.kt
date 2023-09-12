package com.fueled.chatty.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Primary = Color(color = 0xFF1B72C0)
val PrimaryContainer = Color(color = 0xFFD3E4FF)
val OnPrimaryContainer = Color(color = 0xFF001C38)
val PrimarySurface = Color(color = 0xFFF3F4F9)
val SecondarySurface = Color(color = 0xFFEFF1F8)
val Background = Color(color = 0xFFFCFCFF)
val OnSurface20 = Color(color = 0xFF001E2F)
val OnSurface40 = Color(color = 0xFF44474E)
val OnBackground = Color(color = 0xFF001E2F)

val PrimaryDark = Color(color = 0xFFA2C9FF)
val PrimaryContainerDark = Color(color = 0xFF2F4156)
val OnPrimaryContainerDark = Color(color = 0xFFD7E2FF)
val PrimarySurfaceDark = Color(color = 0xFF13232C)
val SecondarySurfaceDark = Color(color = 0xFF1E2A32)
val BackgroundDark = Color(color = 0xFF0E181E)
val OnSurfaceDark20 = Color(color = 0xFFE0F1FF)
val OnSurfaceDark40 = Color(color = 0xFFBFC6DA)
val OnBackgroundDark = Color(color = 0xFFC9E6FF)

val ProjectLightColors = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    surface = PrimarySurface,
    surfaceVariant = SecondarySurface,
    background = Background,
    onSurface = OnSurface20,
    onSurfaceVariant = OnSurface40,
    onBackground = OnBackground,
    secondaryContainer = PrimaryContainer,
)

val ProjectDarkColors = darkColorScheme(
    primary = PrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    surface = PrimarySurfaceDark,
    surfaceVariant = SecondarySurfaceDark,
    background = BackgroundDark,
    onSurface = OnSurfaceDark20,
    onSurfaceVariant = OnSurfaceDark40,
    onBackground = OnBackgroundDark,
    secondaryContainer = PrimaryContainerDark,
)
