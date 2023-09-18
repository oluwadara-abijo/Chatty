package com.fueled.chatty.features.contacts.presentation

import com.fueled.chatty.features.contacts.domain.model.Contact
import com.fueled.chatty.features.contacts.presentation.model.ContactUiModel
import javax.inject.Inject

internal class ContactUiMapper @Inject constructor() {
    fun mapContact(contact: Contact) = ContactUiModel(
        id = contact.id,
        name = contact.name,
        picture = contact.picture,
        status = contact.status,
    )
}
