package com.fueled.chatty.features.chats.presentation

import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.chats.domain.model.Chat
import com.fueled.chatty.features.chats.presentation.list.ChatUiMapper
import com.fueled.chatty.features.chats.presentation.list.model.ChatUiModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ChatsUiMapperTest : BaseTest<ChatUiMapper>() {
    override lateinit var underTest: ChatUiMapper

    @BeforeEach
    fun setup() {
        underTest = ChatUiMapper()
    }

    @Test
    fun `given a chat model, when mapped, then ui model is returned`() {
        // Given
        val fakeChat = Chat(
            id = 1,
            lastChat = "See you soon!",
            latestTimestamp = "12:30",
            name = "Dara",
            picture = "fake_image_url_dara",
        )

        // When
        val result = underTest.mapChat(fakeChat)

        // Then
        result.shouldBeTypeOf<ChatUiModel>()
        with(result) {
            lastChat shouldBe fakeChat.lastChat
            timestamp shouldBe fakeChat.latestTimestamp
            name shouldBe fakeChat.name
            picture shouldBe fakeChat.picture
        }
    }
}
