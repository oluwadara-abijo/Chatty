package com.fueled.chatty.feature.characters.presentation.model

internal data class AppearanceUiModel(
    val available: Int,
    val items: List<String>,
    val type: AppearanceType,
)
