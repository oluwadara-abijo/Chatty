package com.fueled.chatty.feature.contacts.domain

import com.fueled.chatty.core.network.ChatsApi
import com.fueled.chatty.core.network.data.model.ContactApiModel
import com.fueled.chatty.core.network.data.model.ProfileApiModel
import com.fueled.chatty.core.network.data.model.ResponseApiModel
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.contacts.domain.ContactsRepository
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ContactsRepositoryTest : BaseTest<ContactsRepository>() {

    @RelaxedMockK
    private lateinit var fakeChatsApi: ChatsApi
    override lateinit var underTest: ContactsRepository

    @BeforeEach
    fun setup() {
        // Given
        underTest = ContactsRepository(fakeChatsApi)
        every { fakeChatsApi.getChatsData() } returns fakeApiResponse
    }

    @Test
    fun `given json response, when getting all contacts, then all contacts are returned`() {
        startTest {
            // When
            val repositoryResult = underTest.getContacts()

            // Then
            with(repositoryResult) {
                shouldNotBeEmpty()
                size shouldBeEqualComparingTo fakeContactsApiModels.size
            }
        }
    }

    companion object {
        val fakeContactsApiModels = listOf(
            ContactApiModel(
                id = 1,
                name = "Dara",
                picture = "fake_image_url_dara",
                status = "online",
            ),
            ContactApiModel(
                id = 2,
                name = "Anne",
                picture = "fake_image_url_ann",
                status = "offline",
            ),
        )

        val fakeApiResponse = ResponseApiModel(
            contacts = fakeContactsApiModels,
            friends = listOf(),
            profile = ProfileApiModel(
                chatApiModels = listOf(),
                id = 0,
                name = "",
                picture = "",
                status = "",
            ),
        )
    }
}
