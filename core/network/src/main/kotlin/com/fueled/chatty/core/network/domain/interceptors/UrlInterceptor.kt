package com.fueled.chatty.core.network.domain.interceptors

import com.fueled.chatty.core.network.data.ApiConstants
import com.fueled.chatty.core.network.data.ApiUtils
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * As all the Marvel API calls are requiring certain query parameters, instead of adding them to all
 * requests one by one, this interceptor takes care of them in 1 go.
 */
class UrlInterceptor @Inject constructor(private val utils: ApiUtils) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.run {
            proceed(
                request().newBuilder().url(
                    request().url.newBuilder()
                        .addQueryParameter(ApiConstants.Headers.ts, utils.currentTime)
                        .addQueryParameter(ApiConstants.Headers.hash, utils.hash)
                        .addQueryParameter(ApiConstants.Headers.apiKey, ApiConstants.apiKey)
                        .build(),
                ).build(),
            )
        }
    }
}
