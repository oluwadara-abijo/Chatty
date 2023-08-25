package com.fueled.chatty.feature.characters.presentation.list.contract

internal sealed class CharactersViewActions {
    data class OpenCharacterDetail(val charId: Long) : CharactersViewActions()
    object Reload : CharactersViewActions()
    data class SearchCharacters(val query: String) : CharactersViewActions()
}
