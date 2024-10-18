package com.fueled.chatty.features.contacts.presentation.detail

import com.fueled.chatty.core.common.MessageState
import com.fueled.chatty.core.common.contract.BaseState
import com.fueled.chatty.features.contacts.presentation.list.model.ContactUiModel

internal data class ContactDetailState(
    override val isLoading: Boolean,
    override val errorState: MessageState?,
    val contact: ContactUiModel?,
) : BaseState {
    companion object {
        fun initialState() = ContactDetailState(
            isLoading = false,
            errorState = null,
            contact = null,
        )
    }
}
