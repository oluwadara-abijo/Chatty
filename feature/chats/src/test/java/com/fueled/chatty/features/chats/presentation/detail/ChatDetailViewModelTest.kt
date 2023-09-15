package com.fueled.chatty.features.chats.presentation.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.chats.domain.ChatsRepository
import com.fueled.chatty.features.chats.domain.model.ChatDetail
import com.fueled.chatty.features.chats.domain.model.ChatLog
import com.fueled.chatty.features.chats.domain.model.ChatType
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ChatDetailViewModelTest : BaseTest<ChatDetailViewModel>() {

    override lateinit var underTest: ChatDetailViewModel

    @RelaxedMockK
    private lateinit var chatsRepository: ChatsRepository

    @RelaxedMockK
    private lateinit var chatUiMapper: ChatDetailUiMapper

    @Test
    fun `given repository call succeeds, when screen loads, then chat log is displayed`() {
        createViewModel(
            bundle = mapOf(Pair(ChatDetailScreenArgs.EXTRA_FRIEND_ID, fakeFriendId)),
        )
        startTest {
            // When
            coEvery { chatsRepository.getChatLogs(fakeFriendId) } returns fakeChatDetail

            // Then
            underTest.state.test {
                with(awaitItem()) {
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    chatDetail.shouldNotBeNull()
                }
            }
        }
    }

    @Test
    fun `given saved state bundle contains no property, an exception is thrown`() = startTest {
        shouldThrow<IllegalStateException> {
            createViewModel(
                bundle = emptyMap(),
            )
        }
    }

    private fun createViewModel(
        bundle: Map<String, Any>,
    ) {
        underTest = ChatDetailViewModel(
            chatsRepository = chatsRepository,
            uiMapper = chatUiMapper,
            savedStateHandle = SavedStateHandle(bundle),
            dispatcherProvider = dispatcherProvider,
        )
    }

    companion object {
        private const val fakeFriendId = 1

        private val fakeChatDetail = ChatDetail(
            senderName = "Alex",
            senderPicture = "image_url_alex",
            chatLogs = listOf(
                ChatLog(
                    messageId = 99,
                    type = ChatType.Received,
                    text = "Where are you?",
                    timestamp = "12:44 PM",
                ),
                ChatLog(
                    messageId = 98,
                    type = ChatType.Sent,
                    text = "Right behind you",
                    timestamp = "12:46 PM",
                ),
            ),
        )
    }
}
