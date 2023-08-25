package com.fueled.chatty.feature.characters.domain.model

internal enum class ResourceType(val value: String) {
    INTERIOR_STORY("interiorStory"),
    COVER("cover");

    companion object {
        fun fromString(value: String?): ResourceType? = values().firstOrNull() { it.value == value }
    }
}
