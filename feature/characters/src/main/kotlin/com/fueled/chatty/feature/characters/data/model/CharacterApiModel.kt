package com.fueled.chatty.feature.characters.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class CharacterApiModel(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: ImageApiModel,
    val resourceURI: String,
    val comics: ResourceItemsApiModel,
    val series: ResourceItemsApiModel,
    val stories: ResourceItemsApiModel,
    val events: ResourceItemsApiModel,
    val urls: List<UrlApiModel>,
)
