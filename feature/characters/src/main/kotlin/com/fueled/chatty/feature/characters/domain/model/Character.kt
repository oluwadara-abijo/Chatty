package com.fueled.chatty.feature.characters.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Image,
    val resourceURI: String,
    val comics: ResourceItems,
    val series: ResourceItems,
    val stories: ResourceItems,
    val events: ResourceItems,
    val urls: List<Url>,
)
