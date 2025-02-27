package com.fueled.chatty.features.chats.presentation.list

import com.fueled.chatty.features.chats.domain.model.Chat
import com.fueled.chatty.features.chats.presentation.list.model.ChatUiModel
import javax.inject.Inject

internal class ChatUiMapper @Inject constructor() {
    fun mapChat(chat: Chat) = ChatUiModel(
        lastChat = chat.lastChat,
        timestamp = chat.latestTimestamp,
        name = chat.name,
        picture = chat.picture,
        friendId = chat.id,
    )
}
