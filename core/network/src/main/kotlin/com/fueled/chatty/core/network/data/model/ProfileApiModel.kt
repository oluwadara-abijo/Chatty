package com.fueled.chatty.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ProfileApiModel(
    @SerializedName("friends")
    val chatApiModels: List<ChatApiModel>,
    val id: Int,
    val name: String,
    val picture: String,
    val status: String,
)
