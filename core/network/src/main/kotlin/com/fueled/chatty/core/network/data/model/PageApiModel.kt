package com.fueled.chatty.core.network.data.model

import com.squareup.moshi.JsonClass

/**
 * This class is a representation of the pagination used by Marvel API.
 */
@JsonClass(generateAdapter = true)
data class PageApiModel<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>,
)
