package com.fueled.chatty.feature.characters.domain

import com.fueled.chatty.feature.characters.data.model.CharacterApiModel
import com.fueled.chatty.feature.characters.data.model.ImageApiModel
import com.fueled.chatty.feature.characters.data.model.ResourceApiModel
import com.fueled.chatty.feature.characters.data.model.ResourceItemsApiModel
import com.fueled.chatty.feature.characters.data.model.UrlApiModel
import com.fueled.chatty.feature.characters.domain.model.UrlType.DETAIL
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CharacterRepositoryMapperTest {

    @Test
    fun `given a UrlApiModel when mapped then a Url is returned`() {
        // Given / When
        val result = fakeUrl.copy(type = "detail").toEntity()
        // Then
        with(result) {
            url shouldBe fakeUrl.url
            type shouldBe DETAIL
        }
    }

    @Test
    fun `given a ResourceApiModel when mapped then a Resource is returned`() {
        // Given / When
        val result = fakeResource.toEntity()
        // Then
        with(result) {
            resourceURI shouldBe fakeResource.resourceURI
            type shouldBe fakeResource.type
            name shouldBe fakeResource.name
        }
    }

    @Test
    fun `given a ResourceItemsApiModel has a list of resources when mapped then a ResourceItems is returned with Resources`() {
        // Given / When
        val result = fakeResourceItems.toEntity()
        // Then
        with(result) {
            items.count() shouldBe fakeResourceItems.items.count()
        }
    }

    @Test
    fun `given a ResourceItemsApiModel has no resources when mapped then a ResourceItems is returned with empty list`() {
        // Given / When
        val result = fakeResourceItems.copy(items = emptyList()).toEntity()
        // Then
        with(result) {
            items.shouldBeEmpty()
        }
    }

    @Test
    fun `given a CharacterApiModel has a list of urls when mapped then a Character is returned with urls`() {
        // Given / When
        val result = fakeCharacter.toEntity()
        // Then
        with(result) {
            urls.count() shouldBe fakeCharacter.urls.count()
        }
    }

    @Test
    fun `given a CharacterApiModel has no urls when mapped then a Character is returned with empty list of urls`() {
        // Given / When
        val result = fakeCharacter.copy(urls = emptyList()).toEntity()
        // Then
        with(result) {
            urls.shouldBeEmpty()
        }
    }
}

private val fakeUrl = UrlApiModel("detail", "http://www.google.com")
private val fakeResource =
    ResourceApiModel(resourceURI = "http://www.google.com", name = "name", null)
private val fakeResourceItems =
    ResourceItemsApiModel(1, 1, "www.google.com", listOf(fakeResource))

private val fakeImage = ImageApiModel("my_image", ".jpg")
private val fakeCharacter = CharacterApiModel(
    id = 1L,
    name = "Ant Man",
    description = "Teeny tiny ant",
    thumbnail = fakeImage,
    resourceURI = "",
    comics = fakeResourceItems,
    series = fakeResourceItems,
    stories = fakeResourceItems,
    events = fakeResourceItems,
    urls = listOf(fakeUrl),
)
