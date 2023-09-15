package com.fueled.chatty.features.chats.presentation.list

import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.common.contract.ViewEvent
import com.fueled.chatty.features.chats.domain.ChatsRepository
import com.fueled.chatty.features.chats.domain.model.Chat
import com.fueled.chatty.features.chats.presentation.list.ChatsState.Companion.initialState
import com.fueled.chatty.features.chats.presentation.list.ChatsViewAction.OpenChatDetail
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

    override fun onViewAction(viewAction: ChatsViewAction) {
        return when (viewAction) {
            is OpenChatDetail -> navigateToChatDetail(viewAction.friendId)
        }
    }

    override fun handleError(throwable: Throwable) {
        return
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

    private fun navigateToChatDetail(friendId: Int) {
        dispatchViewEvent(ViewEvent.Navigate(ChatsNavigationTargets.ToChatDetail(friendId)))
    }
}
