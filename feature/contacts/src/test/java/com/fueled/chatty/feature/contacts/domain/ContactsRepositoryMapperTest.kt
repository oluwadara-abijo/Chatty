package com.fueled.chatty.feature.contacts.domain

import com.fueled.chatty.core.network.data.model.ContactApiModel
import com.fueled.chatty.features.contacts.domain.mapContact
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ContactsRepositoryMapperTest {

    @Test
    fun `given contact api model, when mapped, then contact is returned`() {
        // When
        val result = fakeContactApiModel.mapContact()

        // Then
        with(result) {
            id shouldBe fakeContactApiModel.id
            name shouldBe fakeContactApiModel.name
            picture shouldBe fakeContactApiModel.picture
            status shouldBe fakeContactApiModel.status
        }
    }

    companion object {
        val fakeContactApiModel = ContactApiModel(
            id = 1,
            name = "Dara",
            picture = "fake_image_url_dara",
            status = "online",
        )
    }
}
