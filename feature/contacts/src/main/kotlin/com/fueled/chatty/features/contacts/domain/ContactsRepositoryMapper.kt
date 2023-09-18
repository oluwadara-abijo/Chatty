package com.fueled.chatty.features.contacts.domain

import com.fueled.chatty.core.network.data.model.ContactApiModel
import com.fueled.chatty.features.contacts.domain.model.Contact

internal fun ContactApiModel.mapContact() = Contact(
    id = id,
    name = name,
    picture = picture,
    status = status,
)
