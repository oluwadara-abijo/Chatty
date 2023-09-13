package com.fueled.chatty.features.chats.domain.model

sealed class ChatType {
    object Sent : ChatType()
    object Received : ChatType()
}
