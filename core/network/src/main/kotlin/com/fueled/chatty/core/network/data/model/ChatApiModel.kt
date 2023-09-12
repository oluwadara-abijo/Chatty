package com.fueled.chatty.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ChatApiModel(
    val id: Int,
    val lastChat: String,
    @SerializedName("latest_timestamp")
    val latestTimestamp: String,
    val name: String,
    val picture: String,
)
