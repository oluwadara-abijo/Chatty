package com.fueled.chatty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.core.ui.navigation.GraphSaver
import com.fueled.chatty.feature.auth.navigation.AuthDestinations
import com.fueled.chatty.feature.auth.navigation.AuthGraph
import com.fueled.chatty.feature.auth.navigation.addLoginScreen
import com.fueled.chatty.feature.characters.navigation.CharactersDestination
import com.fueled.chatty.feature.characters.navigation.CharactersGraph
import com.fueled.chatty.feature.characters.navigation.addCharacterDetailScreen
import com.fueled.chatty.feature.characters.navigation.addCharactersScreen
import com.fueled.chatty.feature.events.navigation.EventsDestination
import com.fueled.chatty.feature.events.navigation.EventsGraph
import com.fueled.chatty.feature.events.navigation.addEventsListScreen

/**
 * This function sets up the main navigation graph.
 * As we have a bottom navigation bar, this sets different navigation graphs for all the bottom items.
 *
 * Based on the current business logic (having a bottom navigation bar) we set a sub-graph
 * for each bottom navigation item, so in order to preserve a back stack for each bottom navigation
 * item.
 *
 * Depending on different business requirements (such as different navigation type of not having a
 * bottom navigation at all) it can happen that there is no need for this level of complexity and
 * everything can be added to a single navigation graph.
 */
@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    setToolbarTitle: (String) -> Unit,
) {
    var rootGraph by rememberSaveable(stateSaver = GraphSaver) { mutableStateOf(AuthGraph) }

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = rootGraph.route,
    ) {
        addAuthGraph(
            onLogin = {
                rootGraph = CharactersGraph
            },
        )
        addCharactersGraph(
            navController = navController,
            setToolbarTitle = setToolbarTitle,
        )
        addEventsGraph(
            navController = navController,
            setToolbarTitle = setToolbarTitle,
            onLogout = {
                rootGraph = AuthGraph
            },
        )
        // ... Other graphs can be added to the main nav host here.
    }
}

private fun NavGraphBuilder.addAuthGraph(
    onLogin: () -> Unit,
    graph: Graph = AuthGraph,
) {
    navigation(
        route = graph.route,
        startDestination = AuthDestinations.Login.createRoute(graph),
    ) {
        addLoginScreen(onLogin = onLogin, graph = graph)
    }
}

private fun NavGraphBuilder.addCharactersGraph(
    navController: NavHostController,
    setToolbarTitle: (String) -> Unit,
    graph: Graph = CharactersGraph,
) {
    navigation(
        route = graph.route,
        startDestination = CharactersDestination.CharacterList.createRoute(graph),
    ) {
        addCharactersScreen(
            graph = graph,
            setToolbarTitle = setToolbarTitle,
            openCharacterDetail = { id ->
                val route = CharactersDestination.CharacterDetail.createRoute(graph, id)
                navController.navigate(route)
            },
        )
        addCharacterDetailScreen(
            graph = graph,
            setToolbarTitle = setToolbarTitle,
        )
    }
}

private fun NavGraphBuilder.addEventsGraph(
    navController: NavHostController,
    setToolbarTitle: (String) -> Unit,
    onLogout: () -> Unit,
    graph: Graph = EventsGraph,
) {
    navigation(
        route = graph.route,
        startDestination = EventsDestination.EventsList.createRoute(graph),
    ) {
        addEventsListScreen(
            graph = graph,
            setToolbarTitle = setToolbarTitle,
            openCharacterDetail = { id ->
                val route = CharactersDestination.CharacterDetail.createRoute(graph, id)
                navController.navigate(route)
            },
            onLogout = onLogout,
        )
        addCharacterDetailScreen(
            graph = graph,
            setToolbarTitle = setToolbarTitle,
        )
    }
}
