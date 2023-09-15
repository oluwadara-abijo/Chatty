package com.fueled.chatty.features.chats.presentation.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.features.chats.presentation.list.components.ChatRow

@Composable
fun ChatsListScreen(
    navigateToChatDetail: (Int) -> Unit,
) {
    ChatsListContent(
        viewModel = hiltViewModel(),
        navigateToChatDetail = navigateToChatDetail,
    )
}

@Composable
private fun ChatsListContent(
    viewModel: ChatsViewModel,
    navigateToChatDetail: (Int) -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ChatsState.initialState())

    Screen {
        LazyColumn(modifier = Modifier.padding(horizontal = SpaceDefault)) {
            item {
                state.chats.map {
                    ChatRow(
                        chat = it,
                        navigateToChatDetail = navigateToChatDetail,
                    )
                }
            }
        }
    }
}
