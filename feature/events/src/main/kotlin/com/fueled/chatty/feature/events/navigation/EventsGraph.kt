package com.fueled.chatty.feature.events.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.feature.events.presentation.EventsScreen

object EventsGraph : Graph("events")

sealed class EventsDestination {

    object EventsList : Destination("events_list")
}

fun NavGraphBuilder.addEventsListScreen(
    setToolbarTitle: (String) -> Unit,
    openCharacterDetail: (Long) -> Unit,
    onLogout: () -> Unit,
    graph: Graph,
) {
    composable(
        route = EventsDestination.EventsList.createRoute(graph),
    ) {
        EventsScreen(
            setToolbarTitle = setToolbarTitle,
            openCharacterDetail = openCharacterDetail,
            onLogout = onLogout,
        )
    }
}
