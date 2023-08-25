package com.fueled.chatty.feature.characters.presentation.model

internal data class CharacterDetailsUiModel(
    val name: String,
    val description: String,
    val imageUrl: String,
    val comics: AppearanceUiModel,
    val series: AppearanceUiModel,
    val stories: AppearanceUiModel,
    val events: AppearanceUiModel,
)
