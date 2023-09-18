package com.fueled.chatty.features.contacts.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.fueled.chatty.features.contacts.presentation.components.ContactsListContent

@Composable
fun ContactsListScreen() {
    ContactsListContent(
        viewModel = hiltViewModel(),
    )
}
