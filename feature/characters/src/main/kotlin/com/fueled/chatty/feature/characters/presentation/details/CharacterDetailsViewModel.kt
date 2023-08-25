package com.fueled.chatty.feature.characters.presentation.details

import androidx.lifecycle.SavedStateHandle
import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.common.Ignored
import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.extensions.collectBy
import com.fueled.chatty.core.ui.util.ErrorHandler
import com.fueled.chatty.feature.characters.domain.CharacterRepository
import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.presentation.details.CharacterDetailScreenArgs.EXTRA_ID
import com.fueled.chatty.feature.characters.presentation.details.contract.CharacterDetailsState
import com.fueled.chatty.feature.characters.presentation.list.toDetailsUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    private val characterRepository: CharacterRepository,
    private val errorHandler: ErrorHandler,
) : BaseViewModel<CharacterDetailsState, Unit>(
    CharacterDetailsState.initialState,
    dispatcherProvider,
) {

    private val characterId: Long = checkNotNull(savedStateHandle[EXTRA_ID])

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            characterRepository.getCharacter(characterId).collectBy(
                onStart = { setIsLoading(true) },
                onEach = ::onCharacterLoaded,
                onError = ::handleError,
            )
        }
    }

    private fun onCharacterLoaded(character: Character) {
        updateState { state ->
            state.copy(
                isLoading = false,
                errorState = null,
                character = character.toDetailsUiModel(),
            )
        }
    }

    override fun onViewAction(viewAction: Unit) = Ignored

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
