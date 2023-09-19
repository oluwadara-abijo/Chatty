package com.fueled.chatty.feature.contacts.presentation

import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.features.contacts.domain.model.Contact
import com.fueled.chatty.features.contacts.presentation.list.ContactUiMapper
import com.fueled.chatty.features.contacts.presentation.list.model.ContactUiModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ContactsUiMapperTest : BaseTest<ContactUiMapper>() {
    override lateinit var underTest: ContactUiMapper

    @BeforeEach
    fun setup() {
        underTest = ContactUiMapper()
    }

    @Test
    fun `given a contact model, when mapped, then ui model is returned`() {
        // Given
        val fakeContact = Contact(
            id = 1,
            name = "Dara",
            picture = "fake_image_url_dara",
            status = "online",
        )

        // When
        val result = underTest.mapContact(fakeContact)

        // Then
        result.shouldBeTypeOf<ContactUiModel>()
        with(result) {
            id shouldBe fakeContact.id
            name shouldBe fakeContact.name
            picture shouldBe fakeContact.picture
            status shouldBe fakeContact.status
        }
    }
}
