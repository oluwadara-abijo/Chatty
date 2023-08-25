package com.fueled.chatty.feature.characters.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Image(val path: String, val extension: String) {
    val url: (ImageVariant) -> String = { iv ->
        if (iv == ImageVariant.FULL_SIZE) {
            "$path.$extension"
        } else {
            "$path/${iv.pathValue}.$extension"
        }
    }
    val defaultUrl = "$path/${ImageVariant.PORTRAIT_INCREDIBLE.pathValue}.$extension"
}
