package com.fueled.chatty.feature.characters.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ImageApiModel(val path: String, val extension: String)
