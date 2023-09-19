package com.fueled.chatty.features.contacts.presentation.list

import com.fueled.chatty.features.contacts.domain.model.Contact
import com.fueled.chatty.features.contacts.presentation.list.model.ContactUiModel
import javax.inject.Inject

internal class ContactUiMapper @Inject constructor() {
    fun mapContact(contact: Contact) = ContactUiModel(
        id = contact.id,
        name = contact.name,
        picture = contact.picture,
        status = contact.status,
    )
}
