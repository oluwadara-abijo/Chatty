package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.data.model.ChatApiModel
import com.fueled.chatty.features.chats.domain.model.Chat

class ChatRepositoryMapper

internal fun ChatApiModel.mapChat() = Chat(
    id = id,
    lastChat = lastChat,
    latestTimestamp = latestTimestamp,
    name = name,
    picture = picture,
)
