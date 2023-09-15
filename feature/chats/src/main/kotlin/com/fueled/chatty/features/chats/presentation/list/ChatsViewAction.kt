package com.fueled.chatty.features.chats.presentation.list

internal sealed class ChatsViewAction {
    data class OpenChatDetail(val friendId: Int) : ChatsViewAction()
}
