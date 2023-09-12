package com.fueled.chatty.features.chats.domain.model

data class Chat(
    val id: Int,
    val lastChat: String,
    val latestTimestamp: String,
    val name: String,
    val picture: String,
)
