package com.fueled.chatty.features.contacts.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fueled.chatty.core.ui.components.LargeAppBar
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.components.SearchBarWidget
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.feature.contacts.R
import com.fueled.chatty.features.contacts.presentation.ContactsState
import com.fueled.chatty.features.contacts.presentation.ContactsViewModel

@Composable
internal fun ContactsListContent(
    viewModel: ContactsViewModel,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ContactsState.initialState())

    Screen {
        Column {
            LargeAppBar(
                title = stringResource(R.string.contacts),
                shouldShowSearch = false,
            )
            SearchBarWidget(searchTerm = "")
            LazyColumn {
                item { state.contacts.map { model -> ContactRow(contact = model) } }
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                onClick = { },
                modifier = Modifier
                    .align(BottomEnd)
                    .padding(SpaceDefault),
                shape = RoundedCornerShape(SpaceDefault),
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    }
}
