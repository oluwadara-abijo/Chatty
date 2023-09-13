package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.ChatsApi
import javax.inject.Inject

internal open class ChatsRepository @Inject constructor(
    private val chatsApi: ChatsApi,
) {

    fun getChats() = fetchChats().map { it.mapChat() }

    fun getChatLogs(id: Int) = fetchChatLogs().find { it.id == id }

    private fun fetchChats() = chatsApi.getChatsData().profile.chatApiModels

    private fun fetchChatLogs() = chatsApi.getChatsData().friends
}
