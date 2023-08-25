package com.fueled.chatty.feature.characters.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ResourceItems(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Resource>,
)
