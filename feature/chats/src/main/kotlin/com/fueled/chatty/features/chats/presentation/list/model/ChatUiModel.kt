package com.fueled.chatty.features.chats.presentation.list.model

data class ChatUiModel(
    val lastChat: String,
    val timestamp: String,
    val name: String,
    val picture: String,
    val friendId: Int,
)
