package com.fueled.chatty.feature.characters.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ResourceItemsApiModel(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<ResourceApiModel>,
)
