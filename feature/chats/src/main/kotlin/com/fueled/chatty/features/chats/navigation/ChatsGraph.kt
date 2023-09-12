package com.fueled.chatty.features.chats.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.features.chats.presentation.ChatsListScreen

object ChatsGraph : Graph("chats")

sealed class ChatsDestination {
    object ChatsList : Destination("list")
}

fun NavGraphBuilder.addChatsListScreen(graph: Graph) {
    composable(route = ChatsDestination.ChatsList.createRoute(graph)) {
        ChatsListScreen()
    }
}
