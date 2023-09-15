package com.fueled.chatty.features.chats.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens
import com.fueled.chatty.features.chats.presentation.detail.components.ChatRowItem

@Composable
fun ChatDetailScreen() {
    ChatDetailContent(viewModel = hiltViewModel())
}

@Composable
private fun ChatDetailContent(viewModel: ChatDetailViewModel) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ChatDetailState.initialState())

    val backgroundColor = if (isSystemInDarkTheme()) {
        colorScheme.background
    } else {
        colorScheme.surface
    }

    Screen(
        modifier = Modifier.background(color = backgroundColor),
    ) {
        LazyColumn(modifier = Modifier.padding(horizontal = Dimens.SpaceDefault)) {
            if (state.chatDetail != null) {
                item {
                    state.chatDetail!!.chatLogs.map {
                        ChatRowItem(
                            chat = it,
                            senderPicture = state.chatDetail!!.senderPicture,
                        )
                    }
                }
            }
        }
    }
}

object ChatDetailScreenArgs {
    const val EXTRA_FRIEND_ID = "friend_id"
}
