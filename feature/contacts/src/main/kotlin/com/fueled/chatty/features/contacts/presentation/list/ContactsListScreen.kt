package com.fueled.chatty.features.contacts.presentation.list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.features.contacts.presentation.list.components.ContactsListContent

@Composable
fun ContactsListScreen() {
    ContactsListContent(
        viewModel = hiltViewModel(),
    )
}
