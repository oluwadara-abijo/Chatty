package com.fueled.chatty.feature.events.presentation.contract

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState

internal class EventsState : BaseState {
    override val isLoading: Boolean = false
    override val errorState: MessageState? = null

    companion object {
        val initialState = EventsState()
    }
}
