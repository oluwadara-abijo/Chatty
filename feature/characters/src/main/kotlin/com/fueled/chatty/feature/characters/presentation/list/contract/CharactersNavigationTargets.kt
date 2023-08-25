package com.fueled.chatty.feature.characters.presentation.list.contract

import com.fueled.chatty.core.common.contract.NavigationTarget

sealed class CharactersNavigationTargets : NavigationTarget {
    data class ToCharacterDetail(val characterId: Long) : CharactersNavigationTargets()
}
