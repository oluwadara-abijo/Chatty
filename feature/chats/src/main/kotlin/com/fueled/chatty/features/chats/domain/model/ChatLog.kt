package com.fueled.chatty.features.chats.domain.model

data class ChatLog(
    val messageId: Int,
    val type: ChatType,
    val text: String,
    val timestamp: String,
)
