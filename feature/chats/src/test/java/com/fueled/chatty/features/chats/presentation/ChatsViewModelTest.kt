package com.fueled.chatty.features.chats.presentation

import app.cash.turbine.test
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.chats.domain.ChatsRepository
import com.fueled.chatty.features.chats.domain.model.Chat
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ChatsViewModelTest : BaseTest<ChatsViewModel>() {

    override lateinit var underTest: ChatsViewModel

    @RelaxedMockK
    private lateinit var testDispatcherProvider: DispatcherProvider

    @RelaxedMockK
    private lateinit var chatsRepository: ChatsRepository

    @RelaxedMockK
    private lateinit var chatUiMapper: ChatUiMapper

    @BeforeEach
    fun setup() {
        // Given
        underTest = ChatsViewModel(
            chatsRepository = chatsRepository,
            uiMapper = chatUiMapper,
            dispatcherProvider = testDispatcherProvider,
        )
    }

    @Test
    fun `given repository call succeeds, when screen loads, then chats are displayed`() {
        startTest {
            // When
            coEvery { chatsRepository.getChats() } returns fakeChats

            // Then
            underTest.state.test {
                with(awaitItem()) {
                    isLoading.shouldBeTrue()
                    errorState.shouldBeNull()
                    chats.shouldBeEmpty()
                }
            }
        }
    }

    companion object {
        val fakeChats = listOf(
            Chat(
                id = 1,
                lastChat = "See you soon!",
                latestTimestamp = "12:30",
                name = "Dara",
                picture = "fake_image_url_dara",
            ),
            Chat(
                id = 2,
                lastChat = "Where are you?",
                latestTimestamp = "14:40",
                name = "Anne",
                picture = "fake_image_url_ann",
            ),
        )
    }
}
