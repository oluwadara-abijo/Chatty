package com.fueled.chatty.navigation

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.fueled.chatty.presentation.components.BottomTab

/**
 * Finds the start destination of the [NavHostController]'s graph and pops the back
 * stack to that destination; while saving and restoring state of the popped and navigated-to
 * routes.
 *
 * This is used in cases where the bottom navigation tabs each house a backstack of destinations
 * which should be saved and restored across navigation targets.
 *
 * @param bottomTab The bottom tab that should be navigated to
 */
internal fun NavHostController.navigateBottomTab(
    bottomTab: BottomTab,
) {
    navigate(bottomTab.graph.route) {
        popUpTo(graph.findStartDestination().id) {
            // Saves the state of the exiting destination, when its popped
            saveState = true
        }
        // Prevent relaunching new instances on re-selecting the same destination
        launchSingleTop = true
        // Restores the state of the new navigated-to destination, if it exists.
        restoreState = true
    }
}
