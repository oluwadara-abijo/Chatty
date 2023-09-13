package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.data.model.ChatApiModel
import com.fueled.chatty.core.network.data.model.ChatLogApiModel
import com.fueled.chatty.core.network.data.model.FriendApiModel
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.chats.domain.model.ChatType
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ChatsRepositoryMapperTest : BaseTest<ChatRepositoryMapper>() {

    override lateinit var underTest: ChatRepositoryMapper

    @BeforeEach
    fun setup() {
        underTest = ChatRepositoryMapper()
    }

    @Test
    fun `given chat api model, when mapped, then chat is returned`() {
        // When
        val result = fakeChatApiModel.mapChat()

        // Then
        with(result) {
            id shouldBe fakeChatApiModel.id
            lastChat shouldBe fakeChatApiModel.lastChat
            latestTimestamp shouldBe fakeChatApiModel.latestTimestamp
            name shouldBe fakeChatApiModel.name
            picture shouldBe fakeChatApiModel.picture
        }
    }

    @Test
    fun `given a sent chat log api model, when mapped, then chat log is returned`() {
        // Given
        val fakeChatLog = fakeChatLogs[0]

        // When
        val result = fakeChatLog.mapChatLog()

        // Then
        with(result) {
            messageId shouldBe fakeChatLog.messageId
            type shouldBe ChatType.Received
            text shouldBe fakeChatLog.text
            timestamp shouldBe fakeChatLog.timestamp
        }
    }

    @Test
    fun `given a received chat log api model, when mapped, then chat log is returned`() {
        // Given
        val fakeChatLog = fakeChatLogs[1]

        // When
        val result = fakeChatLog.mapChatLog()

        // Then
        with(result) {
            messageId shouldBe fakeChatLog.messageId
            type shouldBe ChatType.Sent
            text shouldBe fakeChatLog.text
            timestamp shouldBe fakeChatLog.timestamp
        }
    }

    @Test
    fun `given a friend api model, when mapped, then chat detail is returned`() {
        // When
        val result = fakeFriendApiModel.toChatDetail()

        // Then
        with(result) {
            senderName shouldBe fakeFriendApiModel.name
            senderPicture shouldBe fakeFriendApiModel.picture
            chatLogs.size shouldBe fakeFriendApiModel.chatLogs.size
        }
    }

    @Test
    fun `given a list of chat log api models, when mapped, then a list of chat log models is returned`() {
        // When
        val result = mapChatLogs(fakeChatLogs)

        // Then
        with(result) {
            size shouldBe fakeChatLogs.size
        }
        with(result[0]) {
            messageId shouldBe fakeChatLogs[0].messageId
            text shouldBe fakeChatLogs[0].text
        }
    }

    companion object {
        val fakeChatApiModel = ChatApiModel(
            id = 1,
            lastChat = "See you soon!",
            latestTimestamp = "12:30",
            name = "Dara",
            picture = "fake_image_url_dara",
        )

        val fakeChatLogs = listOf(
            ChatLogApiModel(
                messageId = 2,
                side = "left",
                text = "fake_image_url",
                timestamp = "1:30 PM",
            ),

            ChatLogApiModel(
                messageId = 3,
                side = "right",
                text = "fake_image_url",
                timestamp = "2:30 PM",
            ),
        )

        val fakeFriendApiModel = FriendApiModel(
            chatLogs = listOf(),
            id = 1,
            name = "fake_image_url",
            picture = "2:30 PM",
        )
    }
}
