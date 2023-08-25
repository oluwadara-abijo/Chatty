package com.fueled.chatty.feature.characters.presentation.list

import androidx.lifecycle.SavedStateHandle
import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.ViewEvent.Navigate
import com.fueled.chatty.core.common.extensions.collectBy
import com.fueled.chatty.core.ui.model.Page
import com.fueled.chatty.core.ui.util.ErrorHandler
import com.fueled.chatty.feature.characters.domain.CharacterRepository
import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersNavigationTargets.ToCharacterDetail
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersState
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.OpenCharacterDetail
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.Reload
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.SearchCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DELAY_DURATION = 500L

@HiltViewModel
internal class CharactersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    private val repository: CharacterRepository,
    private val errorHandler: ErrorHandler,
) : BaseViewModel<CharactersState, CharactersViewActions>(
    CharactersState.initialState,
    dispatcherProvider,
) {

    init {
        observeSearchQuery()
    }

    override fun onViewAction(viewAction: CharactersViewActions) {
        return when (viewAction) {
            is OpenCharacterDetail -> navigateToCharacterDetail(viewAction.charId)
            is SearchCharacters -> handleSearchQueryUpdate(viewAction.query.trim())
            Reload -> onReload()
        }
    }

    private fun onReload() {
        launch {
            fetchCharacters()
        }
    }

    private suspend fun fetchCharacters(query: String? = null) {
        repository.getCharacters(query)
            .collectBy(
                onStart = { setIsLoading(true) },
                onEach = ::onPageLoaded,
                onError = ::handleError,
            )
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() = launch {
        state.map { state -> state.searchQuery.ifEmpty { null } }
            .distinctUntilChanged()
            .debounce(DELAY_DURATION)
            .collect(::fetchCharacters)
    }

    private fun onPageLoaded(page: Page<Character>) {
        updateState { state ->
            state.copy(
                isLoading = false,
                data = page.items.map { character -> character.toUiModel() },
                errorState = null,
            )
        }
    }

    private fun navigateToCharacterDetail(characterId: Long) {
        dispatchViewEvent(
            Navigate(ToCharacterDetail(characterId)),
        )
    }

    private fun handleSearchQueryUpdate(query: String) {
        updateState { state -> state.copy(searchQuery = query) }
    }

    override fun handleError(throwable: Throwable) {
        updateState { state ->
            state.copy(
                isLoading = false,
                errorState = MessageState.Snack(
                    message = errorHandler.getErrorMessage(throwable),
                ),
            )
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        updateState { state ->
            state.copy(
                isLoading = isLoading,
                errorState = null,
            )
        }
    }
}
