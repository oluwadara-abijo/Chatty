package com.fueled.chatty.feature.characters.domain.model

internal enum class ImageVariant(val pathValue: String) {
    PORTRAIT_SMALL("portrait_small"),
    PORTRAIT_MEDIUM("portrait_medium"),
    PORTRAIT__LARGE("portrait__large"),
    PORTRAIT_FANTASTIC("portrait_fantastic"),
    PORTRAIT_UNCANNY("portrait_uncanny"),
    PORTRAIT_INCREDIBLE("portrait_incredible"),
    STANDARD_SMALL("standard_small"),
    STANDARD_MEDIUM("standard_medium"),
    STANDARD_LARGE("standard_large"),
    STANDARD__LARGE("standard__large"),
    STANDARD_FANTASTIC("standard_fantastic"),
    STANDARD_AMAZING("standard_amazing"),
    LANDSCAPE_SMALL("landscape_small"),
    LANDSCAPE_MEDIUM("landscape_medium"),
    LANDSCAPE_LARGE("landscape_large"),
    LANDSCAPE__LARGE("landscape__large"),
    LANDSCAPE_AMAZING("landscape_amazing"),
    LANDSCAPE_INCREDIBLE("landscape_incredible"),
    DETAIL("detail"),
    FULL_SIZE("")
}
