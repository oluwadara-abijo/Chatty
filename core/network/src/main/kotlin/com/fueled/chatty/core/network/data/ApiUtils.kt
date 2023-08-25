package com.fueled.chatty.core.network.data

import java.security.MessageDigest
import javax.inject.Inject

/**
 * ApiUtils is needed for the Marvel API - it requires the client to send a timestamp (ts)
 * and a hash which is the md5 encoded value of the timestamp the clientId, and the API key.
 * Besides the above the API Key also needs to be sent.
 * This class just provide a helper function to get the correct hash calculated using the actual timestamp.
 */
class ApiUtils @Inject constructor(private val messageDigest: MessageDigest) {
    val currentTime: String
        get() = System.currentTimeMillis().toString()

    val hash
        get() = md5(currentTime + ApiConstants.clientId + ApiConstants.apiKey)

    private fun md5(str: String): String {
        val byteArray = str.toByteArray()
        val digestedByteArray = messageDigest.digest(byteArray)
        return digestedByteArray.toHex()
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}
