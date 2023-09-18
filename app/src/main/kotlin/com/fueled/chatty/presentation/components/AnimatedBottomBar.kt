package com.fueled.chatty.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.features.chats.navigation.ChatsDestination.ChatsList
import com.fueled.chatty.features.chats.navigation.ChatsGraph
import com.fueled.chatty.features.contacts.navigation.ContactsDestination.ContactsList
import com.fueled.chatty.features.contacts.navigation.ContactsGraph

/**
 * Wraps the BottomNavigation setup, handles click propagation to parent and selected/unselected state
 */
@Composable
fun AnimatedBottomBar(
    isVisible: Boolean,
    onBottomTabSelected: (BottomTab) -> Unit,
    currentDestination: NavDestination?,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it * 2 }),
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 0.dp,
        ) {
            BOTTOM_TABS.forEach { tab ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == tab.graph.route } == true
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (selected) tab.selectedIcon else tab.unselectedIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    },
                    label = { Text(text = stringResource(id = tab.iconTextId)) },
                    selected = selected,
                    onClick = { onBottomTabSelected(tab) },
                )
            }
        }
    }
}

/**
 * Routes for the different Bottom Tabs in the application.
 * BottomTabs in the application are Navigation holders and each will contain their own Navigation stack.
 *
 * Navigation from one screen to the next within a single destination will be handled directly in composables.
 *
 * @property graph The graph that belongs to this bottom nav item. This will be used to determine
 * which icon should appear as selected, when a particular [Graph.route] is in the backstack.
 * @property startDestRoute The route for the starting [Destination] of this section. Will be used
 * to determine if the current displayed screen is in fact a TopLevel Destination.
 */
data class BottomTab(
    val graph: Graph,
    val startDestRoute: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
)

val BOTTOM_TABS = listOf(
    BottomTab(
        graph = ChatsGraph,
        startDestRoute = ChatsList.createRoute(ChatsGraph),
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.chats,
    ),
    BottomTab(
        graph = ContactsGraph,
        startDestRoute = ContactsList.createRoute(ContactsGraph),
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.Outlined.People,
        iconTextId = R.string.contacts,
    ),
    BottomTab(
        graph = ChatsGraph,
        startDestRoute = ChatsList.createRoute(ChatsGraph),
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        iconTextId = R.string.settings,
    ),
)
