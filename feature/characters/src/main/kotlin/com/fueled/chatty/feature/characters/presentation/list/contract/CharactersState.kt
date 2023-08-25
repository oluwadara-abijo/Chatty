package com.fueled.chatty.feature.characters.presentation.list.contract

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState
import com.fueled.chatty.feature.characters.presentation.model.CharacterUiModel
import javax.annotation.concurrent.Immutable

@Immutable
internal data class CharactersState(
    override val isLoading: Boolean = false,
    override val errorState: MessageState? = null,
    val data: List<CharacterUiModel> = emptyList(),
    val searchQuery: String = "",
) : BaseState {
    companion object {
        val initialState = CharactersState()
    }
}
