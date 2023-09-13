package com.fueled.chatty.features.chats.domain.model

/**
 * Represents a single chat message, which could be of type [ChatType.Sent] or [ChatType.Received]
 */
internal data class ChatLog(
    val messageId: Int,
    val type: ChatType,
    val text: String,
    val timestamp: String,
)
