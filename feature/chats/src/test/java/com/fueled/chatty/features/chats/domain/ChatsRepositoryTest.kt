package com.fueled.chatty.features.chats.domain

import com.fueled.chatty.core.network.ChatsApi
import com.fueled.chatty.core.network.data.model.ChatApiModel
import com.fueled.chatty.core.network.data.model.ProfileApiModel
import com.fueled.chatty.core.network.data.model.ResponseApiModel
import com.fueled.chatty.core.testing.BaseTest
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
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

    companion object {
        val fakeApiModels = listOf(
            ChatApiModel(
                id = 1,
                lastChat = "See you soon!",
                latestTimestamp = "12:30",
                name = "Dara",
                picture = "fake_image_url_dara",
            ),
            ChatApiModel(
                id = 2,
                lastChat = "Where are you?",
                latestTimestamp = "14:40",
                name = "Anne",
                picture = "fake_image_url_ann",
            ),
        )

        val fakeApiResponse = ResponseApiModel(
            contacts = listOf(),
            friends = listOf(),
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
