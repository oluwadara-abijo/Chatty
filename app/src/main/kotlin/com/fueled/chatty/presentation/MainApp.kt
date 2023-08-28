package com.fueled.chatty.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fueled.chatty.core.ui.theme.ProjectTheme
import com.fueled.chatty.navigation.MainNavHost
import com.fueled.chatty.navigation.navigateBottomTab
import com.fueled.chatty.presentation.components.AnimatedBottomBar
import com.fueled.chatty.presentation.components.BOTTOM_TABS

/**
 * The base structure, scaffolding of the app, holding together the different parts;
 * Toolbar, BottomNav and content.
 *
 * This also handles window insets and together with Theme.kt file status bar coloring.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun MainApp() {
    val navController = rememberNavController()
    val topLevelRoutes by remember { mutableStateOf(BOTTOM_TABS.map { it.startDestRoute }) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isTopLevel: Boolean = currentDestination?.route in topLevelRoutes

    ProjectTheme {
        Scaffold(
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .background(MaterialTheme.colors.primary)
                .systemBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                AnimatedBottomBar(
                    isVisible = isTopLevel,
                    onBottomTabSelected = navController::navigateBottomTab,
                    currentDestination = currentDestination,
                )
            },
        ) { paddingValues ->
            MainNavHost(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues),
                navController = navController,
            )
        }
    }
}
