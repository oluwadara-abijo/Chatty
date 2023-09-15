package com.fueled.chatty.features.chats.presentation.list

import com.fueled.chatty.core.common.contract.NavigationTarget

sealed class ChatsNavigationTargets : NavigationTarget {
    data class ToChatDetail(val friendId: Int) : ChatsNavigationTargets()
}
