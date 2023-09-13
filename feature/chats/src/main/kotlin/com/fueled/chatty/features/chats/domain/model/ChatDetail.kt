package com.fueled.chatty.features.chats.domain.model

data class ChatDetail(
    val messageId: Int,
    val type: ChatType,
    val text: String,
    val timestamp: String,
)

sealed class ChatType {
    object Sent : ChatType()
    object Received : ChatType()
}
