package com.fueled.chatty.features.chats.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceLarge
import com.fueled.chatty.features.chats.presentation.detail.components.ChatRowItem

@Composable
fun ChatDetailScreen(
    navigateUp: () -> Unit,
) {
    ChatDetailContent(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp,
    )
}

@Composable
private fun ChatDetailContent(
    viewModel: ChatDetailViewModel,
    navigateUp: () -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ChatDetailState.initialState())

    Screen {
        Column {
            AppBar(
                friendName = state.chatDetail?.senderName ?: "",
                navigateUp = navigateUp,
            )

            LazyColumn(modifier = Modifier.padding(horizontal = SpaceDefault)) {
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    friendName: String,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.background(color = colorScheme.background),
        title = {
            Text(
                text = friendName,
                modifier = Modifier.padding(horizontal = SpaceDefault),
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            Icon(imageVector = Icons.Filled.Call, contentDescription = null)
            Spacer(modifier = Modifier.width(SpaceLarge))
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(SpaceLarge))
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorScheme.background),
    )
}

object ChatDetailScreenArgs {
    const val EXTRA_FRIEND_ID = "friend_id"
}
