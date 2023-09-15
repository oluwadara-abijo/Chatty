package com.fueled.chatty.features.chats.presentation.detail.model

import com.fueled.chatty.features.chats.domain.model.ChatType

internal data class ChatLogUiModel(
    val text: String,
    val timeStamp: String,
    val type: ChatType,
)
