package com.fueled.chatty.features.chats.presentation.list

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState
import com.fueled.chatty.features.chats.presentation.list.model.ChatUiModel

internal data class ChatsState(
    override val isLoading: Boolean,
    override val errorState: MessageState?,
    val chats: List<ChatUiModel>,
) : BaseState {
    companion object {
        fun initialState() = ChatsState(
            isLoading = false,
            errorState = null,
            chats = emptyList(),
        )
    }
}
