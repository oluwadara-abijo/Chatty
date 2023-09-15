package com.fueled.chatty.features.chats.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.features.chats.domain.ChatsRepository
import com.fueled.chatty.features.chats.domain.model.ChatDetail
import com.fueled.chatty.features.chats.presentation.detail.ChatDetailState.Companion.initialState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ChatDetailViewModel @Inject constructor(
    private val chatsRepository: ChatsRepository,
    private val uiMapper: ChatDetailUiMapper,
    private val savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<ChatDetailState, ChatDetailViewAction>(initialState(), dispatcherProvider) {

    private val friendId: Int = checkNotNull(savedStateHandle[ChatDetailScreenArgs.EXTRA_FRIEND_ID])

    init {
        getChatDetail()
    }

    private fun getChatDetail() {
        val chatDetail = chatsRepository.getChatLogs(friendId)
        updateChatDetail(chatDetail)
    }

    private fun updateChatDetail(chatDetail: ChatDetail) {
        updateState { state ->
            state.copy(
                chatDetail = uiMapper.mapChatDetail(chatDetail),
            )
        }
    }

    override fun onViewAction(viewAction: ChatDetailViewAction) {
        return
    }

    override fun handleError(throwable: Throwable) {
        return
    }
}
