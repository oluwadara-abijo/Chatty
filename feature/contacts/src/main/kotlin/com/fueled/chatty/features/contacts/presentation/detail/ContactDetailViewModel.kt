package com.fueled.chatty.features.contacts.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.features.contacts.domain.ContactsRepository
import com.fueled.chatty.features.contacts.presentation.detail.ContactDetailScreenArgs.EXTRA_CONTACT_ID
import com.fueled.chatty.features.contacts.presentation.detail.ContactDetailState.Companion.initialState
import com.fueled.chatty.features.contacts.presentation.list.ContactUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ContactDetailViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository,
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    private val uiMapper: ContactUiMapper,
) : BaseViewModel<ContactDetailState, ContactDetailViewAction>(initialState(), dispatcherProvider) {

    private val contactId: Int = checkNotNull(savedStateHandle[EXTRA_CONTACT_ID])

    init {
        loadData()
    }

    override fun onViewAction(viewAction: ContactDetailViewAction) {
        return
    }

    override fun handleError(throwable: Throwable) {
        return
    }

    private fun loadData() {
        val contact = contactsRepository.getContactById(contactId)?.let { uiMapper.mapContact(it) }
        if (contact != null) {
            updateState { state ->
                state.copy(
                    contact = contact

                )
            }
        }
    }

}
