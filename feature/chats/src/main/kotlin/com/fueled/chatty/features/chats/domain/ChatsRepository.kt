package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.ChatsApi
import javax.inject.Inject

internal open class ChatsRepository @Inject constructor(
    private val chatsApi: ChatsApi,
) {

    open fun getChats() = fetchChats().map { it.mapChat() }

    private fun fetchChats() = chatsApi.getChatsData().profile.chatApiModels
}
