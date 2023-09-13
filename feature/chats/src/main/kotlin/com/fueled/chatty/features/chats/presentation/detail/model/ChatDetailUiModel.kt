package com.fueled.chatty.features.chats.presentation.detail.model

internal data class ChatDetailUiModel(
    val senderName: String,
    val senderPicture: String,
    val chatLogs: List<ChatLogUiModel>,
)
