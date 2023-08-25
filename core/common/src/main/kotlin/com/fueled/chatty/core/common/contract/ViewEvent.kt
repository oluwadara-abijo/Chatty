package com.fueled.chatty.core.common.contract

import com.fueled.chatty.core.common.MessageState

sealed class ViewEvent {
    data class Navigate(val target: NavigationTarget) : ViewEvent()
    data class DisplayMessage(val message: MessageState) : ViewEvent()
    data class Effect(val effect: SideEffect) : ViewEvent()
}
