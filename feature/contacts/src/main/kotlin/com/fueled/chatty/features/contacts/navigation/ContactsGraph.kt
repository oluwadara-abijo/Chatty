package com.fueled.chatty.features.contacts.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.features.contacts.navigation.ContactsDestination.ContactsList
import com.fueled.chatty.features.contacts.presentation.ContactsListScreen

object ContactsGraph : Graph("contacts")

sealed class ContactsDestination {
    object ContactsList : Destination("list")
}

fun NavGraphBuilder.addContactsListScreen(
    graph: Graph,
) {
    composable(route = ContactsList.createRoute(graph)) {
        ContactsListScreen()
    }
}
