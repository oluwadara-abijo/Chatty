package com.fueled.chatty.features.chats.presentation

import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.chats.domain.model.ChatDetail
import com.fueled.chatty.features.chats.domain.model.ChatLog
import com.fueled.chatty.features.chats.domain.model.ChatType
import com.fueled.chatty.features.chats.presentation.detail.ChatDetailUiMapper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ChatDetailUiMapperTest : BaseTest<ChatDetailUiMapper>() {
    override lateinit var underTest: ChatDetailUiMapper

    @BeforeEach
    fun setup() {
        underTest = ChatDetailUiMapper()
    }

    @Test
    fun `given a chat log domain model, when mapped, then a ui model is returned`() {
        // Given
        val fakeChatLog = fakeChatLogs[0]

        // When
        val result = underTest.mapChatLog(fakeChatLog)

        // Then
        with(result) {
            text shouldBe fakeChatLog.text
            timeStamp shouldBe fakeChatLog.timestamp
        }
    }

    @Test
    fun `given a chat detail domain model, when mapped, then a ui model is returned`() {
        // Given
        val chatDetail = fakChatDetail

        // When
        val result = underTest.mapChatDetail(chatDetail)

        // Then
        with(result) {
            senderName shouldBe chatDetail.senderName
            senderPicture shouldBe chatDetail.senderPicture
            chatLogs.size shouldBe chatDetail.chatLogs.size
        }
    }

    companion object {
        val fakeChatLogs = listOf(
            ChatLog(
                messageId = 1,
                type = ChatType.Received,
                text = "I'll be there in 5.",
                timestamp = "12:30 PM",
            ),
            ChatLog(
                messageId = 2,
                type = ChatType.Sent,
                text = "See you soon!",
                timestamp = "12:32 PM",
            ),
        )

        val fakChatDetail = ChatDetail(
            senderName = "Dara",
            senderPicture = "image_dara",
            chatLogs = fakeChatLogs,
        )
    }
}
