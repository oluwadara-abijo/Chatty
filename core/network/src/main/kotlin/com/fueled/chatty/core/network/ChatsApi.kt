package com.fueled.chatty.core.network

import javax.inject.Inject

open class ChatsApi @Inject constructor(private val parser: Parser) {

    open fun getChatsData() = parser.parseResponse()
}
