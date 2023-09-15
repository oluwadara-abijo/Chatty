package com.fueled.chatty.features.chats.presentation.detail

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState
import com.fueled.chatty.features.chats.presentation.detail.model.ChatDetailUiModel

internal data class ChatDetailState(
    override val isLoading: Boolean,
    override val errorState: MessageState?,
    val chatDetail: ChatDetailUiModel?,
) : BaseState {
    companion object {
        fun initialState() = ChatDetailState(
            isLoading = false,
            errorState = null,
            chatDetail = null,
        )
    }
}
