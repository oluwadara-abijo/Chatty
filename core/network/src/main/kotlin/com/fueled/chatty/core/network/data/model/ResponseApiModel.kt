package com.fueled.chatty.core.network.data.model

import com.google.gson.annotations.SerializedName

data class ResponseApiModel(
    @SerializedName("allFriends")
    val contacts: List<ContactApiModel>,
    val friends: List<FriendApiModel>,
    val profile: ProfileApiModel,
)
