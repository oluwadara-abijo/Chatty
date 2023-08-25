package com.fueled.chatty.feature.characters.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class UrlApiModel(val type: String, val url: String)
