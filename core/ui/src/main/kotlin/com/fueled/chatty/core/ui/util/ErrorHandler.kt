package com.fueled.chatty.core.ui.util

import coil.network.HttpException
import com.fueled.chatty.core.common.StringProvider
import com.fueled.chatty.core.ui.R
import javax.inject.Inject

interface ErrorHandler {

    fun getErrorMessage(error: Throwable): String
}

class ErrorHandlerImpl @Inject constructor(
    private val stringProvider: StringProvider,
) : ErrorHandler {

    override fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is HttpException -> "Network failed"
            else -> stringProvider.getString(R.string.common_error)
        }
    }
}
