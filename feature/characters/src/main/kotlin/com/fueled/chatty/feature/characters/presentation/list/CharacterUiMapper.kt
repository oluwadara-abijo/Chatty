package com.fueled.chatty.feature.characters.presentation.list

import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.domain.model.ResourceItems
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.COMIC
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.EVENT
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.SERIES
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.STORY
import com.fueled.chatty.feature.characters.presentation.model.AppearanceUiModel
import com.fueled.chatty.feature.characters.presentation.model.CharacterDetailsUiModel
import com.fueled.chatty.feature.characters.presentation.model.CharacterUiModel

internal fun Character.toUiModel() = CharacterUiModel(
    id = id,
    name = name,
    imageUrl = thumbnail.defaultUrl,
)

internal fun Character.toDetailsUiModel() = CharacterDetailsUiModel(
    name = name,
    description = description,
    imageUrl = thumbnail.defaultUrl,
    comics = comics.toCharacterAppearance(COMIC),
    series = series.toCharacterAppearance(SERIES),
    stories = stories.toCharacterAppearance(STORY),
    events = events.toCharacterAppearance(EVENT),
)

internal fun ResourceItems.toCharacterAppearance(type: AppearanceType) = AppearanceUiModel(
    available = available,
    items = items.map { resource -> resource.name },
    type = type,
)
