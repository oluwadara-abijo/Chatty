package com.fueled.chatty.core.network.data.model

import com.google.gson.annotations.SerializedName

data class FriendApiModel(
    @SerializedName("chatlog")
    val chatLogs: List<ChatLogApiModel>,
    val id: Int,
    val name: String,
    val picture: String,
)
