package com.fueled.chatty.core.network.data

import com.fueled.chatty.core.network.data.model.ResponseApiModel
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * As all Marvel API responses are wrapped into an "Envelope", instead of always unwrapping it on
 * the call site, with a Retrofit Converter we unwrap all calls.
 */
class UnWrapper<T>(private val converter: Converter<ResponseBody, ResponseApiModel<T>>?) :
    Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? = converter?.convert(value)?.data
}
