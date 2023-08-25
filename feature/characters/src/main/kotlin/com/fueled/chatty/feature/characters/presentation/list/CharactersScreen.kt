package com.fueled.chatty.feature.characters.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.core.common.Ignored
import com.fueled.chatty.core.common.contract.ViewEvent.Navigate
import com.fueled.chatty.core.common.extensions.ifNotEmpty
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.components.InlineErrorStateButton
import com.fueled.chatty.core.ui.components.InlineErrorStateView
import com.fueled.chatty.core.ui.extensions.collectAsEffect
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.feature.characters.presentation.list.components.CharactersContent
import com.fueled.chatty.feature.characters.presentation.list.components.CharactersEmpty
import com.fueled.chatty.feature.characters.presentation.list.components.CharactersLoading
import com.fueled.chatty.feature.characters.presentation.list.components.SearchWidget
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersNavigationTargets.ToCharacterDetail
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersState
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.OpenCharacterDetail
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.Reload
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.SearchCharacters

@Composable
fun CharactersScreen(
    openCharacterDetail: (Long) -> Unit,
    setToolbarTitle: (String) -> Unit,
) {
    setToolbarTitle(stringResource(id = R.string.characters))
    CharactersScreenContent(
        viewModel = hiltViewModel(),
        openCharacterDetailScreen = openCharacterDetail,
    )
}

@Composable
private fun CharactersScreenContent(
    viewModel: CharactersViewModel,
    openCharacterDetailScreen: (Long) -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharactersState.initialState)

    viewModel.events.collectAsEffect { event ->
        when (event) {
            is Navigate -> {
                when (val target = event.target) {
                    is ToCharacterDetail -> openCharacterDetailScreen(target.characterId)
                }
            }
            else -> Ignored
        }
    }

    Column {
        SearchWidget(
            onQueryChanged = { query ->
                viewModel.onViewAction(SearchCharacters(query))
            },
            state.searchQuery,
        )

        if (state.data.isEmpty() && !state.isLoading) {
            CharactersEmpty()
        } else if (state.isLoading) {
            CharactersLoading()
        }

        state.data.ifNotEmpty {
            CharactersContent(
                data = this,
                onCharacterSelected = { selectedCharacterId ->
                    viewModel.onViewAction(
                        OpenCharacterDetail(
                            selectedCharacterId,
                        ),
                    )
                },
            )
        }

        state.errorState?.run {
            InlineErrorStateView(
                errorText = stringResource(id = R.string.common_error),
                actionButton = InlineErrorStateButton(
                    text = stringResource(id = R.string.common_retry),
                    action = { viewModel.onViewAction(Reload) },
                    icon = Icons.Default.Refresh,
                ),
            )
        }
    }
}
