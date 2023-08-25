package com.fueled.chatty.feature.characters.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Resource(val resourceURI: String, val name: String, val type: ResourceType?)
