package com.fueled.chatty.feature.characters.presentation.details.contract

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState
import com.fueled.chatty.feature.characters.presentation.model.CharacterDetailsUiModel

internal data class CharacterDetailsState(
    override val isLoading: Boolean = false,
    override val errorState: MessageState? = null,
    val character: CharacterDetailsUiModel? = null,
) : BaseState {
    companion object {
        val initialState = CharacterDetailsState()
    }
}
