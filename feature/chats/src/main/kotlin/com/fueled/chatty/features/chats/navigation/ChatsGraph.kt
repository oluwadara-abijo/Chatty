package com.fueled.chatty.features.chats.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.features.chats.navigation.ChatsDestination.ChatDetail
import com.fueled.chatty.features.chats.navigation.ChatsDestination.ChatsList
import com.fueled.chatty.features.chats.presentation.detail.ChatDetailScreen
import com.fueled.chatty.features.chats.presentation.detail.ChatDetailScreenArgs.EXTRA_FRIEND_ID
import com.fueled.chatty.features.chats.presentation.list.ChatsListScreen

object ChatsGraph : Graph("chats")

sealed class ChatsDestination {
    object ChatsList : Destination("list")

    object ChatDetail : Destination("detail/{$EXTRA_FRIEND_ID}") {
        fun createRoute(graph: Graph, friendId: Int): String {
            return "${graph.route}/detail/$friendId"
        }
    }
}

fun NavGraphBuilder.addChatsListScreen(
    graph: Graph,
    navigateToChatDetail: (Int) -> Unit,
) {
    composable(route = ChatsList.createRoute(graph)) {
        ChatsListScreen(
            navigateToChatDetail = navigateToChatDetail,
        )
    }
}

fun NavGraphBuilder.addChatDetailScreen(
    graph: Graph,
    navigateUp: () -> Unit,
) {
    composable(
        route = ChatDetail.createRoute(graph),
        arguments = listOf(
            navArgument(EXTRA_FRIEND_ID) { type = NavType.IntType },
        ),
    ) {
        ChatDetailScreen(navigateUp = navigateUp)
    }
}
