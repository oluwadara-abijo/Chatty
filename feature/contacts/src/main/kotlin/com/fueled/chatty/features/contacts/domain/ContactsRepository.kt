package com.fueled.chatty.features.contacts.domain

import com.fueled.chatty.core.network.ChatsApi
import com.fueled.chatty.core.network.data.model.ContactApiModel
import com.fueled.chatty.features.contacts.domain.model.Contact
import javax.inject.Inject

internal class ContactsRepository @Inject constructor(
    private val chatsApi: ChatsApi,
) {

    fun getContacts(): List<Contact> {
        return fetchContacts().map { it.mapContact() }
    }

    private fun fetchContacts(): List<ContactApiModel> {
        return chatsApi.getChatsData().contacts
    }
}
