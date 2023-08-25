package com.fueled.chatty.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.theme.ProjectTheme
import com.fueled.chatty.feature.auth.navigation.AuthGraph
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
    var toolbarText by rememberSaveable { mutableStateOf("") }
    val isTopLevel: Boolean = currentDestination?.route in topLevelRoutes
    val isAuthRoute = (currentDestination?.parent?.route ?: "") == AuthGraph.route

    ProjectTheme {
        Scaffold(
            modifier = Modifier
                .semantics { testTagsAsResourceId = true }
                .background(MaterialTheme.colors.primary)
                .systemBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                AnimatedBottomBar(
                    isVisible = !isAuthRoute,
                    onBottomTabSelected = navController::navigateBottomTab,
                    currentDestination = currentDestination,
                )
            },
            topBar = {
                if (!isAuthRoute) {
                    TopAppBar(
                        title = { Text(text = toolbarText, style = MaterialTheme.typography.h1) },
                        navigationIcon = if (isTopLevel) {
                            null
                        } else {
                            {
                                IconButton(onClick = navController::navigateUp) {
                                    Icon(
                                        painter = rememberImagePainter(R.drawable.ic_back),
                                        contentDescription = null,
                                    )
                                }
                            }
                        },
                    )
                }
            },
        ) { paddingValues ->
            MainNavHost(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(paddingValues),
                navController = navController,
                setToolbarTitle = { title -> toolbarText = title },
            )
        }
    }
}
