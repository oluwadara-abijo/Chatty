package com.fueled.chatty.feature.characters.domain.model

internal enum class UrlType(val value: String) {
    DETAIL("detail"), WIKI("wiki"), COMIC_LINK("comiclink"), OTHER("");

    companion object {
        fun fromString(value: String): UrlType = values().firstOrNull { it.value == value } ?: OTHER
    }
}
