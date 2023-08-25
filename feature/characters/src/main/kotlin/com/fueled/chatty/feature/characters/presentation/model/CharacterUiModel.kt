package com.fueled.chatty.feature.characters.presentation.model

/**
 * This is a lightweight representation of the character data for the grid of characters
 */
internal data class CharacterUiModel(
    val id: Long,
    val name: String,
    val imageUrl: String,
)
