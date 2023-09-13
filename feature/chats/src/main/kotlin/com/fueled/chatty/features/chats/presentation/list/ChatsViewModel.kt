package com.fueled.chatty.features.chats.presentation.list

import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.features.chats.domain.ChatsRepository
import com.fueled.chatty.features.chats.domain.model.Chat
import com.fueled.chatty.features.chats.presentation.list.ChatsState.Companion.initialState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ChatsViewModel @Inject constructor(
    private val chatsRepository: ChatsRepository,
    private val uiMapper: ChatUiMapper,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<ChatsState, ChatsViewAction>(initialState(), dispatcherProvider) {

    init {
        getChats()
    }

    private fun getChats() {
        updateState { state ->
            state.copy(
                isLoading = true,
            )
        }
        val chatList = chatsRepository.getChats()
        updateChatList(chatList)
    }

    private fun updateChatList(chatList: List<Chat>) {
        updateState { state ->
            state.copy(
                chats = chatList.map { uiMapper.mapChat(it) },
            )
        }
    }

    override fun onViewAction(viewAction: ChatsViewAction) {
        return
    }

    override fun handleError(throwable: Throwable) {
        return
    }
}
