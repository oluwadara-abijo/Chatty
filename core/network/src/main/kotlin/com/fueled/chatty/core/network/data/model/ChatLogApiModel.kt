package com.fueled.chatty.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ChatLogApiModel(
    @SerializedName("message_id")
    val messageId: Int,
    val side: String,
    val text: String,
    val timestamp: String,
)
