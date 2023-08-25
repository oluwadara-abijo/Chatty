package com.fueled.chatty.feature.characters.domain

import com.fueled.chatty.core.network.data.model.PageApiModel
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.core.ui.model.Page
import com.fueled.chatty.feature.characters.data.CharactersApi
import com.fueled.chatty.feature.characters.data.model.CharacterApiModel
import com.fueled.chatty.feature.characters.data.model.ImageApiModel
import com.fueled.chatty.feature.characters.data.model.ResourceApiModel
import com.fueled.chatty.feature.characters.data.model.ResourceItemsApiModel
import com.fueled.chatty.feature.characters.data.model.UrlApiModel
import com.fueled.chatty.feature.characters.domain.model.Character
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExtendWith(MockKExtension::class)
internal class CharacterRepositoryTest : BaseTest<CharacterRepository>() {

    @RelaxedMockK
    private lateinit var mockApi: CharactersApi
    override lateinit var underTest: CharacterRepository

    @BeforeEach
    fun setup() {
        underTest = CharacterRepository(mockApi, dispatcherProvider)
    }

    @Test
    fun `Given API returns an empty list When getting any character Then it should not emit any results`() =
        startTest {
            // Given
            coEvery { mockApi.getCharacter(any()) } returns Response.success(
                PageApiModel(
                    offset = 0,
                    limit = 0,
                    total = 0,
                    count = 0,
                    results = emptyList(),
                ),
            )

            // When
            val result = underTest.getCharacter(0L).toList()

            // Then
            result.shouldBeEmpty()
        }

    @Test
    fun `Given API returns content When getting any character Then it should should emit a character`() =
        startTest {
            // Given
            val fakeApiModel = fakeCharacterApiModel()
            val expectedCharacter = fakeApiModel.toEntity()
            coEvery { mockApi.getCharacter(any()) } returns Response.success(
                PageApiModel(
                    offset = 0,
                    limit = 0,
                    total = 0,
                    count = 0,
                    results = listOf(fakeApiModel),
                ),
            )

            // When
            val result = underTest.getCharacter(0L).toList()

            // Then
            result.shouldContainExactly(expectedCharacter)
        }

    @Test
    fun `Given api fails When requesting any character Then it propagates exception`() = startTest {
        // Given
        coEvery { mockApi.getCharacter(any()) } throws IllegalStateException()

        // When / Then
        shouldThrow<IllegalStateException> { underTest.getCharacter(0L).toList() }
    }

    @Test
    fun `Given API returns an empty list When requesting a list of characters Then it should emit an empty page`() =
        startTest {
            // Given
            val expectedResult = Page<Character>(emptyList())
            coEvery { mockApi.getCharacters(any()) } returns Response.success(
                PageApiModel(
                    offset = 0,
                    limit = 0,
                    total = 0,
                    count = 0,
                    results = emptyList(),
                ),
            )

            // When
            val result = underTest.getCharacters().toList()

            // Then
            result.shouldContainExactly(expectedResult)
        }

    @Test
    fun `Given API returns a list of content When requesting the list of characters Then it should emit a page with the characters`() =
        startTest {
            // Given
            val fakeApiModel = fakeCharacterApiModel()
            val expectedResult = Page(listOf(fakeApiModel.toEntity()))
            coEvery { mockApi.getCharacters(any()) } returns Response.success(
                PageApiModel(
                    offset = 0,
                    limit = 0,
                    total = 0,
                    count = 0,
                    results = listOf(fakeApiModel),
                ),
            )

            // When
            val result = underTest.getCharacters().toList()

            // Then
            result.shouldContainExactly(expectedResult)
        }

    @Test
    fun `Given api fails When requesting a list of characters Then it propagates exception`() =
        startTest {
            // Given
            coEvery { mockApi.getCharacters(any()) } throws IllegalStateException()

            // When / Then
            shouldThrow<IllegalStateException> { underTest.getCharacters().toList() }
        }

    @Test
    fun `Given API returns an empty list When fetching a character Then it should emit an empty page`() =
        startTest {
            // Given
            val expected = Page<Character>(emptyList())
            coEvery { mockApi.getCharacters(any()) } returns Response.success(
                PageApiModel(
                    offset = 0,
                    limit = 0,
                    total = 0,
                    count = 0,
                    results = emptyList(),
                ),
            )

            // When
            val result = underTest.getCharacters("any").toList()

            // Then
            result.shouldContainExactly(expected)
        }
}

private fun fakeCharacterApiModel(
    id: Long = 0,
    name: String = "",
    description: String = "",
    thumbnail: ImageApiModel = fakeImageApiModel(),
    resourceURI: String = "",
    comics: ResourceItemsApiModel = fakeResourceItemsApiModel(),
    series: ResourceItemsApiModel = fakeResourceItemsApiModel(),
    stories: ResourceItemsApiModel = fakeResourceItemsApiModel(),
    events: ResourceItemsApiModel = fakeResourceItemsApiModel(),
    urls: List<UrlApiModel> = emptyList(),
) = CharacterApiModel(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    resourceURI = resourceURI,
    comics = comics,
    series = series,
    stories = stories,
    events = events,
    urls = urls,
)

private fun fakeResourceItemsApiModel(
    available: Int = 0,
    returned: Int = 0,
    collectionURI: String = "",
    items: List<ResourceApiModel> = emptyList(),
) = ResourceItemsApiModel(
    available = available,
    returned = returned,
    collectionURI = collectionURI,
    items = items,
)

private fun fakeImageApiModel(
    path: String = "",
    extension: String = "",
) = ImageApiModel(
    path = path,
    extension = extension,
)
