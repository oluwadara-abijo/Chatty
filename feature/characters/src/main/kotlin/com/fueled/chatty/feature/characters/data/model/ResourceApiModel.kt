package com.fueled.chatty.feature.characters.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ResourceApiModel(val resourceURI: String, val name: String, val type: String?)
