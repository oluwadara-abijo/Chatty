package com.fueled.chatty.feature.events.presentation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.components.CenterColumn
import com.fueled.chatty.core.ui.components.Screen

@Composable
fun EventsScreen(
    setToolbarTitle: (String) -> Unit,
    openCharacterDetail: (Long) -> Unit,
    onLogout: () -> Unit,
) {
    setToolbarTitle(stringResource(id = R.string.events))
    EventsScreenContent(
        viewModel = hiltViewModel(),
        onCharacterClick = openCharacterDetail,
        onLogout = onLogout,
    )
}

@Suppress("UNUSED_PARAMETER")
@Composable
private fun EventsScreenContent(
    viewModel: EventsViewModel,
    onCharacterClick: (Long) -> Unit,
    onLogout: () -> Unit,
) {
    Screen {
        CenterColumn {
            Button(onClick = { onCharacterClick(CHARACTER_ID) }) {
                Text(text = "Open 3D Man Detail Screen")
            }
            Button(onClick = onLogout) {
                Text(text = "Logout")
            }
        }
    }
}

private const val CHARACTER_ID = 1011334L
