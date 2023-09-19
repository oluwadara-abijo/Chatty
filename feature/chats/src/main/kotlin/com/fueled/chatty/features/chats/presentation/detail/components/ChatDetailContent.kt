package com.fueled.chatty.features.chats.presentation.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.features.chats.presentation.detail.ChatDetailState
import com.fueled.chatty.features.chats.presentation.detail.ChatDetailViewModel

@Composable
internal fun ChatDetailContent(
    viewModel: ChatDetailViewModel,
    navigateUp: () -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ChatDetailState.initialState())

    Screen {
        Column {
            AppBar(
                title = state.chatDetail?.senderName ?: "",
                navigateUp = navigateUp,
            )

            LazyColumn(modifier = Modifier.padding(horizontal = SpaceDefault)) {
                if (state.chatDetail != null) {
                    items(state.chatDetail!!.chatLogs) {
                        ChatRowItem(
                            chat = it,
                            senderPicture = state.chatDetail!!.senderPicture,
                        )
                    }
                }
            }

            Box(modifier = Modifier.fillMaxHeight()) {
                ChatDetailFooter(
                    modifier = Modifier.align(BottomCenter),
                )
            }
        }
    }
}
