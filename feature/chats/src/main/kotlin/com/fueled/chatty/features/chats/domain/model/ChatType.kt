package com.fueled.chatty.features.chats.domain.model

/**
 * Sealed class signifying the chat type.
 * Sent chats - [ChatType.Sent] are displayed on the right.
 * Received chats - [ChatType.Received] are displayed on the left
 */
internal sealed class ChatType {
    object Sent : ChatType()
    object Received : ChatType()
}
