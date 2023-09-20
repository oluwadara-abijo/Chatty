package com.fueled.chatty.feature.contacts.presentation.list

import app.cash.turbine.test
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.common.contract.ViewEvent.Navigate
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.contacts.domain.ContactsRepository
import com.fueled.chatty.features.contacts.domain.model.Contact
import com.fueled.chatty.features.contacts.presentation.list.ContactUiMapper
import com.fueled.chatty.features.contacts.presentation.list.ContactsNavigationTargets.ToContactDetail
import com.fueled.chatty.features.contacts.presentation.list.ContactsViewAction
import com.fueled.chatty.features.contacts.presentation.list.ContactsViewModel
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ContactsViewModelTest : BaseTest<ContactsViewModel>() {

    override lateinit var underTest: ContactsViewModel

    @RelaxedMockK
    private lateinit var testDispatcherProvider: DispatcherProvider

    @RelaxedMockK
    private lateinit var contactsRepository: ContactsRepository

    @RelaxedMockK
    private lateinit var chatUiMapper: ContactUiMapper

    @BeforeEach
    fun setup() {
        // Given
        every { contactsRepository.getContacts() } returns fakeContacts
        underTest = ContactsViewModel(
            contactsRepository = contactsRepository,
            uiMapper = chatUiMapper,
            dispatcherProvider = testDispatcherProvider,
        )
    }

    @Test
    fun `given repository call succeeds, when screen loads, then contacts are displayed`() {
        startTest {
            // Then
            underTest.state.test {
                with(awaitItem()) {
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    contacts.shouldNotBeEmpty()
                    contacts.size shouldBe fakeContacts.size
                }
            }
        }
    }

    @Test
    fun `given contacts list is displayed, when a contact is clicked, then we navigate to contact detail screen`() {
        // TODO Fix failing test
        startTest {
            val fakeContactId = fakeContacts[0].id

            underTest.events.test {
                // When
                underTest.onViewAction(ContactsViewAction.OpenContactDetail(fakeContactId))

                with(awaitItem()) {
                    shouldBeInstanceOf<Navigate>()
                    target.shouldBeInstanceOf<ToContactDetail>()
                    (target as ToContactDetail).contactId shouldBe fakeContactId
                }
            }
        }
    }

    companion object {
        val fakeContacts = listOf(
            Contact(
                id = 1,
                name = "Dara",
                picture = "fake_image_url_dara",
                status = "online",
            ),
            Contact(
                id = 2,
                name = "Anne",
                picture = "fake_image_url_ann",
                status = "offline",
            ),
        )
    }
}
