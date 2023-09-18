package com.fueled.chatty.features.contacts.presentation

import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.features.contacts.domain.ContactsRepository
import com.fueled.chatty.features.contacts.domain.model.Contact
import com.fueled.chatty.features.contacts.presentation.ContactsState.Companion.initialState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ContactsViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository,
    private val uiMapper: ContactUiMapper,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<ContactsState, ContactsViewAction>(initialState(), dispatcherProvider) {

    init {
        getContacts()
    }

    override fun onViewAction(viewAction: ContactsViewAction) {
        return
    }

    override fun handleError(throwable: Throwable) {
        return
    }

    private fun getContacts() {
        updateState { state ->
            state.copy(
                isLoading = true,
            )
        }
        val contactList = contactsRepository.getContacts()
        updateContactsList(contactList)
    }

    private fun updateContactsList(contactList: List<Contact>) {
        updateState { state ->
            state.copy(
                contacts = contactList.map { uiMapper.mapContact(it) },
            )
        }
    }
}
