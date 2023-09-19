package com.fueled.chatty.features.contacts.presentation.list

internal sealed class ContactsViewAction {
    data class OpenContactDetail(val contactId: Int) : ContactsViewAction()
}
