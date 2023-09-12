package com.fueled.chatty.core.network.data.model

data class FriendApiModel(
    val chatLogs: List<ChatLogApiModel>,
    val id: Int,
    val name: String,
    val picture: String,
)
