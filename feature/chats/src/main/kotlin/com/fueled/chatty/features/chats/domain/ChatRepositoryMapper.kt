package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.data.model.ChatApiModel
import com.fueled.chatty.core.network.data.model.ChatLogApiModel
import com.fueled.chatty.core.network.data.model.FriendApiModel
import com.fueled.chatty.features.chats.domain.model.Chat
import com.fueled.chatty.features.chats.domain.model.ChatDetail
import com.fueled.chatty.features.chats.domain.model.ChatLog
import com.fueled.chatty.features.chats.domain.model.ChatType

class ChatRepositoryMapper

internal fun ChatApiModel.mapChat() = Chat(
    id = id,
    lastChat = lastChat,
    latestTimestamp = latestTimestamp,
    name = name,
    picture = picture,
)

fun mapChatType(type: String): ChatType =
    if (type == "left") ChatType.Received else ChatType.Sent

fun ChatLogApiModel.mapChatLog() = ChatLog(
    messageId = messageId,
    type = mapChatType(side),
    text = text,
    timestamp = timestamp,
)

fun mapChatLogs(chatLogs: List<ChatLogApiModel>): List<ChatLog> = chatLogs.map { it.mapChatLog() }

fun FriendApiModel.toChatDetail() = ChatDetail(
    senderName = name,
    senderPicture = picture,
    chatLogs = mapChatLogs(chatLogs),
)
