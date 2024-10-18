package com.fueled.chatty.features.contacts.presentation.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.fueled.chatty.core.common.Ignored
import com.fueled.chatty.core.common.contract.ViewEvent
import com.fueled.chatty.core.ui.components.LargeAppBar
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.components.SearchBarWidget
import com.fueled.chatty.core.ui.extensions.collectAsEffect
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.feature.contacts.R
import com.fueled.chatty.features.contacts.presentation.list.ContactsNavigationTargets.ToContactDetail
import com.fueled.chatty.features.contacts.presentation.list.ContactsState
import com.fueled.chatty.features.contacts.presentation.list.ContactsViewAction
import com.fueled.chatty.features.contacts.presentation.list.ContactsViewAction.OpenContactDetail
import com.fueled.chatty.features.contacts.presentation.list.ContactsViewModel

@Composable
internal fun ContactsListContent(
    viewModel: ContactsViewModel,
    navigateToContactDetail: (Int) -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ContactsState.initialState())

    viewModel.events.collectAsEffect { event ->
        when (event) {
            is ViewEvent.Navigate -> {
                when (val target = event.target) {
                    is ToContactDetail -> navigateToContactDetail(target.contactId)
                }
            }

            else -> Ignored
        }
    }

    Screen {
        Column {
            LargeAppBar(
                title = stringResource(R.string.contacts),
                shouldShowSearch = false,
            )
            SearchBarWidget(searchTerm = "")
            LazyColumn {
                items(state.contacts) { contact ->
                    ContactRow(
                        contact = contact,
                        navigateToContactDetail = { id ->
                            viewModel.onViewAction(OpenContactDetail(id))
                        },
                    )
                }
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
