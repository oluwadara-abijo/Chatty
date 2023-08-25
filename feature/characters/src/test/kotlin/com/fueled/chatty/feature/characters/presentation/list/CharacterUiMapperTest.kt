package com.fueled.chatty.feature.characters.presentation.list

import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.domain.model.Image
import com.fueled.chatty.feature.characters.domain.model.Resource
import com.fueled.chatty.feature.characters.domain.model.ResourceItems
import com.fueled.chatty.feature.characters.domain.model.Url
import com.fueled.chatty.feature.characters.domain.model.UrlType
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CharacterUiMapperTest {

    @Test
    fun `Given a Character, When mapped to ui model, Then a ui model is returned`() {
        // Given / When
        val result = fakeCharacter.toUiModel()

        // Then
        result.id shouldBe fakeCharacter.id
        result.name shouldBe fakeCharacter.name
        result.imageUrl shouldBe fakeCharacter.thumbnail.defaultUrl
    }

    @Test
    fun `Given a character, When mapped to detail ui model, Then a ui model is returned`() {
        // Given / When
        val result = fakeCharacter.toDetailsUiModel()

        // Then
        result.name shouldBe fakeCharacter.name
        result.description shouldBe fakeCharacter.description
        result.imageUrl shouldBe fakeCharacter.thumbnail.defaultUrl
    }

    @Test
    fun `Given a resource item, When mapped, Then the correct type is returned`() {
        // Given / When
        val expectedType = AppearanceType.COMIC
        val result = fakeResourceItems.toCharacterAppearance(expectedType)
        result.items.count() shouldBe fakeResourceItems.items.count()
        result.type shouldBe expectedType
    }
}

private val fakeResource = Resource(resourceURI = "", name = "", type = null)
private val fakeResourceItems = ResourceItems(0, 0, "", listOf(fakeResource))
private val fakeCharacter = Character(
    id = 1L,
    name = "Daffy Duck",
    description = "Black and white duck",
    thumbnail = Image(path = "", extension = ".png"),
    resourceURI = "",
    comics = fakeResourceItems,
    series = fakeResourceItems,
    stories = fakeResourceItems,
    events = fakeResourceItems,
    urls = listOf(Url(UrlType.DETAIL, "")),
)
