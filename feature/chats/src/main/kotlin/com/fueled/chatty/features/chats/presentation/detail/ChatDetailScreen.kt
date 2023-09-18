package com.fueled.chatty.features.chats.presentation.detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.features.chats.presentation.detail.components.ChatDetailContent

@Composable
fun ChatDetailScreen(
    navigateUp: () -> Unit,
) {
    ChatDetailContent(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
    )
}

object ChatDetailScreenArgs {
    const val EXTRA_FRIEND_ID = "friend_id"
}
