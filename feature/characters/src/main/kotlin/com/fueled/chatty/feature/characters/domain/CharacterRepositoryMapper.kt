package com.fueled.chatty.feature.characters.domain

import com.fueled.chatty.feature.characters.data.model.CharacterApiModel
import com.fueled.chatty.feature.characters.data.model.ImageApiModel
import com.fueled.chatty.feature.characters.data.model.ResourceApiModel
import com.fueled.chatty.feature.characters.data.model.ResourceItemsApiModel
import com.fueled.chatty.feature.characters.data.model.UrlApiModel
import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.domain.model.Image
import com.fueled.chatty.feature.characters.domain.model.Resource
import com.fueled.chatty.feature.characters.domain.model.ResourceItems
import com.fueled.chatty.feature.characters.domain.model.ResourceType
import com.fueled.chatty.feature.characters.domain.model.Url
import com.fueled.chatty.feature.characters.domain.model.UrlType

internal fun UrlApiModel.toEntity() = Url(UrlType.fromString(type), url)
internal fun ResourceApiModel.toEntity() =
    Resource(resourceURI, name, ResourceType.fromString(type))

internal fun ResourceItemsApiModel.toEntity() =
    ResourceItems(available, returned, collectionURI, items.map { it.toEntity() })

internal fun ImageApiModel.toEntity() = Image(path, extension)
internal fun CharacterApiModel.toEntity() = Character(
    id,
    name,
    description,
    thumbnail.toEntity(),
    resourceURI,
    comics.toEntity(),
    series.toEntity(),
    stories.toEntity(),
    events.toEntity(),
    urls.map { it.toEntity() },
)
