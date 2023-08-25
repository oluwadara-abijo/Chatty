package com.fueled.chatty.core.network.data.model

import com.squareup.moshi.JsonClass

/**
 * All marvel response is wrapped by this structure - this is removed on a converter level
 *
 * possible improvements: adding http code and etag handling
 */
@JsonClass(generateAdapter = true)
data class ResponseApiModel<T>(val code: Int, val status: String, val etag: String, val data: T)
