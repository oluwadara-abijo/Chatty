package com.fueled.chatty.core.network

import android.content.Context
import com.fueled.chatty.core.network.data.model.ResponseApiModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import javax.inject.Inject

class Parser @Inject constructor(private val context: Context, private val gson: Gson) {

    private fun type(): Type = object : TypeToken<ResponseApiModel>() {}.type

    fun parseResponse(): ResponseApiModel {
        return gson.fromJson(getJsonResponse(), type())
    }

    private fun getJsonResponse(): String {
        val resources = context.applicationContext.resources
        val inputStream = resources.openRawResource(R.raw.chats_data)
        val inputStreamReader = InputStreamReader(inputStream)
        return BufferedReader(inputStreamReader).use { it.readText() }
    }
}
