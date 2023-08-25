package com.fueled.chatty.core.network.data

object ApiConstants {

    const val url = "https://gateway.marvel.com/v1/public/"
    const val clientId = "a0729b5c16d407c88ac13f2e072c334f6c6f62ae"
    const val apiKey = "3ad1d3cb617f6003c2a2ae853daa4eef"

    object Headers {
        const val ts = "ts"
        const val hash = "hash"
        const val apiKey = "apikey"
        const val query = "nameStartsWith"
    }
}
