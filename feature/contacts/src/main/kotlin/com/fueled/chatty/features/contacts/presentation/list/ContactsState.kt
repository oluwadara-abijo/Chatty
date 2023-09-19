package com.fueled.chatty.features.contacts.presentation.list

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState
import com.fueled.chatty.features.contacts.presentation.list.model.ContactUiModel

internal data class ContactsState(
    override val isLoading: Boolean,
    override val errorState: MessageState?,
    val contacts: List<ContactUiModel>,
) : BaseState {
    companion object {
        fun initialState() = ContactsState(
            isLoading = false,
            errorState = null,
            contacts = emptyList(),
        )
    }
}
