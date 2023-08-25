package com.fueled.chatty.feature.characters

import com.fueled.chatty.core.ui.util.UiTag

sealed class CharactersTag(override val key: String) : UiTag {
    object SearchField : CharactersTag("auto_search")
    object CharacterCard : CharactersTag("auto_character")
}
