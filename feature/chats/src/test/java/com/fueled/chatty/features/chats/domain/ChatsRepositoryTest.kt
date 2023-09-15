package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.ChatsApi
import com.fueled.chatty.core.network.data.model.ChatApiModel
import com.fueled.chatty.core.network.data.model.ChatLogApiModel
import com.fueled.chatty.core.network.data.model.FriendApiModel
import com.fueled.chatty.core.network.data.model.ProfileApiModel
import com.fueled.chatty.core.network.data.model.ResponseApiModel
import com.fueled.chatty.core.testing.BaseTest
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ChatsRepositoryTest : BaseTest<ChatsRepository>() {

    @RelaxedMockK
    private lateinit var fakeChatsApi: ChatsApi
    override lateinit var underTest: ChatsRepository

    @BeforeEach
    fun setup() {
        underTest = ChatsRepository(fakeChatsApi)
    }

    @Test
    fun `given json response, when getting all chats, then all chats are returned`() {
        startTest {
            // Given
            every { fakeChatsApi.getChatsData() } returns fakeApiResponse

            // When
            val repositoryResult = underTest.getChats()

            // Then
            with(repositoryResult) {
                size shouldBeEqualComparingTo fakeApiModels.size
            }
        }
    }

    @Test
    fun `given a friend id, when getting chat log, then chat log for that id is returned`() {
        startTest {
            // Given
            every { fakeChatsApi.getChatsData() } returns fakeApiResponse

            val fakeFriend = fakeFriends[0]

            // When
            val repositoryResult = underTest.getChatLogs(fakeFriend.id)

            // Then
            with(repositoryResult) {
                shouldNotBeNull()
                senderName shouldBe "Friend 34"
                senderPicture shouldBe "picture_thirty_four"
                chatLogs.size shouldBe 2
            }
        }
    }

    @Test
    fun `given a null friend id, when getting chat log, then no chat log is returned`() {
        startTest {
            // Given
            every { fakeChatsApi.getChatsData() } returns fakeApiResponse

            val fakeFriendId = 100

            // When
            val repositoryResult = underTest.getChatLogs(fakeFriendId)

            // Then
            with(repositoryResult) {
                shouldBeNull()
            }
        }
    }

    companion object {
        val fakeApiModels = listOf(
            ChatApiModel(
                id = 1,
                lastChat = "See you soon!",
                latestTimestamp = "12:30 PM",
                name = "Dara",
                picture = "fake_image_url_dara",
            ),
            ChatApiModel(
                id = 2,
                lastChat = "Where are you?",
                latestTimestamp = "1:40 PM",
                name = "Anne",
                picture = "fake_image_url_ann",
            ),
        )

        private val fakeChatLogsOne = listOf(
            ChatLogApiModel(
                messageId = 91,
                side = "Left",
                text = "Hey, what's up?",
                timestamp = "9:34 AM",
            ),
            ChatLogApiModel(
                messageId = 92,
                side = "Left",
                text = "I'm good. How are you too?",
                timestamp = "9:35 AM",
            ),
        )

        private val fakeChatLogsTwo = listOf(
            ChatLogApiModel(
                messageId = 93,
                side = "Right",
                text = "Are you coming tonight?",
                timestamp = "9:40 AM",
            ),
            ChatLogApiModel(
                messageId = 94,
                side = "Right",
                text = "Sure, I will!",
                timestamp = "9:41 AM",
            ),
            ChatLogApiModel(
                messageId = 95,
                side = "Left",
                text = "Okay then; see ya",
                timestamp = "9:48 AM",
            ),
        )

        private val fakeFriends = listOf(
            FriendApiModel(
                chatLogs = fakeChatLogsOne,
                id = 34,
                name = "Friend 34",
                picture = "picture_thirty_four",
            ),
            FriendApiModel(
                chatLogs = fakeChatLogsTwo,
                id = 35,
                name = "Friend 35",
                picture = "picture_thirty_five",
            ),
        )

        val fakeApiResponse = ResponseApiModel(
            contacts = listOf(),
            friends = fakeFriends,
            profile = ProfileApiModel(
                chatApiModels = fakeApiModels,
                id = 1,
                name = "name",
                picture = "picture",
                status = "status",
            ),
        )
    }
}
