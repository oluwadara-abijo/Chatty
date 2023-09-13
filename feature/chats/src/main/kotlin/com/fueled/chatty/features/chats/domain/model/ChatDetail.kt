package com.fueled.chatty.features.chats.domain.model

data class ChatDetail(
    val senderName: String,
    val senderPicture: String,
    val chatLogs: List<ChatLog>,
)
