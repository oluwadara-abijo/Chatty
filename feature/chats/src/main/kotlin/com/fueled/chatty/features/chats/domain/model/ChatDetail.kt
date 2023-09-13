package com.fueled.chatty.features.chats.domain.model

/**
 * Model containing all the data needed on a chat detail screen
 * [senderName] will be displayed on the toolbar
 * [senderPicture] will be displayed on received chats
 * [chatLogs] is a list of all chats, both [ChatType.Sent] and [ChatType.Received]
 */
internal data class ChatDetail(
    val senderName: String,
    val senderPicture: String,
    val chatLogs: List<ChatLog>,
)
