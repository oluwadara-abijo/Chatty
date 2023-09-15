package com.fueled.chatty.features.chats.presentation.detail

import com.fueled.chatty.features.chats.domain.model.ChatDetail
import com.fueled.chatty.features.chats.domain.model.ChatLog
import com.fueled.chatty.features.chats.presentation.detail.model.ChatDetailUiModel
import com.fueled.chatty.features.chats.presentation.detail.model.ChatLogUiModel
import javax.inject.Inject

internal class ChatDetailUiMapper @Inject constructor() {
    fun mapChatLog(chatLog: ChatLog) = ChatLogUiModel(
        text = chatLog.text,
        timeStamp = chatLog.timestamp,
        type = chatLog.type,
    )

    private fun mapChatLogs(chatLogs: List<ChatLog>) = chatLogs.map { mapChatLog(it) }

    fun mapChatDetail(chatDetail: ChatDetail) = ChatDetailUiModel(
        senderName = chatDetail.senderName,
        senderPicture = chatDetail.senderPicture,
        chatLogs = mapChatLogs(chatDetail.chatLogs),
    )
}
