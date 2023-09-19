package com.fueled.chatty.features.contacts.presentation.list

import com.fueled.chatty.core.common.contract.NavigationTarget

sealed class ContactsNavigationTargets : NavigationTarget {
    data class ToContactDetail(val contactId: Int) : ContactsNavigationTargets()
}
