package com.fueled.chatty.core.common.extensions

inline fun <T> List<T>.ifNotEmpty(
    block: List<T>.() -> Unit,
) {
    if (isNotEmpty()) block()
}
