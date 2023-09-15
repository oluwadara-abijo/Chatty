package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.ChatsApi
import com.fueled.chatty.features.chats.domain.model.ChatDetail
import javax.inject.Inject

internal open class ChatsRepository @Inject constructor(
    private val chatsApi: ChatsApi,
) {

    fun getChats() = fetchChats().map { it.mapChat() }

    fun getChatLogs(id: Int): ChatDetail {
        val friendApiModel = fetchChatLogs().find { it.id == id }!!
        return friendApiModel.toChatDetail()
    }

    private fun fetchChats() = chatsApi.getChatsData().profile.chatApiModels

    private fun fetchChatLogs() = chatsApi.getChatsData().friends
}
