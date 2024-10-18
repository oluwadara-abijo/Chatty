package com.fueled.chatty.features.contacts.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.features.contacts.navigation.ContactsDestination.ContactDetail
import com.fueled.chatty.features.contacts.navigation.ContactsDestination.ContactsList
import com.fueled.chatty.features.contacts.presentation.detail.ContactDetailScreen
import com.fueled.chatty.features.contacts.presentation.detail.ContactDetailScreenArgs.EXTRA_CONTACT_ID
import com.fueled.chatty.features.contacts.presentation.list.ContactsListScreen

object ContactsGraph : Graph("contacts")

sealed class ContactsDestination {
    object ContactsList : Destination("list")

    object ContactDetail : Destination("detail/{$EXTRA_CONTACT_ID}") {
        fun createRoute(graph: Graph, contactId: Int): String {
            return "${graph.route}/detail/$contactId"
        }
    }
}

fun NavGraphBuilder.addContactsListScreen(
    graph: Graph,
    navigateToContactDetail: (Int) -> Unit,
) {
    composable(route = ContactsList.createRoute(graph)) {
        ContactsListScreen(navigateToContactDetail = navigateToContactDetail)
    }
}

fun NavGraphBuilder.addContactDetailScreen(
    graph: Graph,
    navigateUp: () -> Unit,
) {
    composable(
        route = ContactDetail.createRoute(graph),
        arguments = listOf(
            navArgument(EXTRA_CONTACT_ID) { type = NavType.IntType },
        ),
    ) {
        ContactDetailScreen(navigateUp = navigateUp)
    }
}
