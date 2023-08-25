package com.fueled.chatty.feature.characters.presentation.list

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.fueled.chatty.core.common.contract.ViewEvent
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.core.ui.model.Page
import com.fueled.chatty.core.ui.util.ErrorHandler
import com.fueled.chatty.feature.characters.domain.CharacterRepository
import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.domain.model.Image
import com.fueled.chatty.feature.characters.domain.model.ResourceItems
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersNavigationTargets.ToCharacterDetail
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.OpenCharacterDetail
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.Reload
import com.fueled.chatty.feature.characters.presentation.list.contract.CharactersViewActions.SearchCharacters
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeEmpty
import io.kotest.matchers.string.shouldStartWith
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CharacterViewModelTest : BaseTest<CharactersViewModel>() {

    @RelaxedMockK
    private lateinit var repository: CharacterRepository

    @RelaxedMockK
    private lateinit var errorHandler: ErrorHandler
    override lateinit var underTest: CharactersViewModel

    @Test
    fun `Given repository call succeeds when screen is loaded then characters are displayed`() =
        startTest {
            // Given
            val fakeFlow = flow {
                delay(500) // Imitates request of API or DB call being made
                emit(fakeCharactersPage)
            }
            coEvery { repository.getCharacters() } returns fakeFlow

            createViewModel()

            // Then
            underTest.state.test {
                // Initial State
                with(awaitItem()) {
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    data.shouldBeEmpty()
                }
                // Loading State
                with(awaitItem()) {
                    isLoading.shouldBeTrue()
                    errorState.shouldBeNull()
                    data.shouldBeEmpty()
                }

                // Data State
                with(awaitItem()) {
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    data.shouldNotBeEmpty()
                }
            }
        }

    @Test
    fun `Given repository call fails when screen is loaded then error is displayed`() = startTest {
        // Given
        val fakeFlow = flow<Page<Character>> {
            delay(500) // Imitates request of API or DB call being made
            throw IllegalStateException()
        }
        coEvery { repository.getCharacters() } returns fakeFlow

        createViewModel()

        // Then
        underTest.state.test {
            // Initial State
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                errorState.shouldBeNull()
                data.shouldBeEmpty()
            }
            // Loading State
            with(awaitItem()) {
                isLoading.shouldBeTrue()
                errorState.shouldBeNull()
                data.shouldBeEmpty()
            }

            // Data State
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                errorState.shouldNotBeNull()
                data.shouldBeEmpty()
            }
        }
    }

    @Test
    fun `Given a repository call fails when user clicks on reload then data is fetched and displayed`() =
        startTest {
            // Given
            val fakeFlowError = flow<Page<Character>> {
                delay(500)
                throw IllegalStateException()
            }
            val fakeFlowSucceed = flow {
                delay(500) // Imitates request of API or DB call being made
                emit(fakeCharactersPage)
            }
            coEvery { repository.getCharacters() } returns fakeFlowError andThen fakeFlowSucceed

            createViewModel()

            underTest.state.test {
                awaitItem() // Initial State
                awaitItem() // Loading State
                awaitItem() // Error State

                // When
                underTest.onViewAction(Reload)

                // Then
                with(awaitItem()) { // Loading State
                    isLoading.shouldBeTrue()
                    errorState.shouldBeNull()
                    data.shouldBeEmpty()
                }

                with(awaitItem()) { // Data State
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    data.shouldNotBeEmpty()
                }
            }
        }

    @Test
    fun `When user clicks on a character we navigate to detail screen`() = startTest {
        // Given
        val fakeFlow = flow {
            delay(500) // Imitates request of API or DB call being made
            emit(fakeCharactersPage)
        }
        coEvery { repository.getCharacters() } returns fakeFlow

        createViewModel()

        val expectedCharacterId = 1L
        underTest.events.test {
            // When
            underTest.onViewAction(OpenCharacterDetail(expectedCharacterId))

            with(awaitItem()) {
                shouldBeInstanceOf<ViewEvent.Navigate>()
                target.shouldBeInstanceOf<ToCharacterDetail>()
                (target as ToCharacterDetail).characterId shouldBe expectedCharacterId
            }
        }
    }

    @Test
    fun `When user filters characters by name then only filtered characters are displayed`() = startTest {
        // Given
        val testSearchQuery = "Spi"

        val fakeFlow = flow {
            delay(500) // Imitates request of API or DB call being made

            val apiResult = Page(
                fakeCharactersPage.items.filter { character ->
                    character.name.startsWith(testSearchQuery, ignoreCase = true)
                },
            )
            emit(apiResult)
        }

        coEvery { repository.getCharacters(testSearchQuery) } returns fakeFlow

        createViewModel()

        underTest.state.test {
            // When
            underTest.onViewAction(SearchCharacters(testSearchQuery))

            // Initial State
            awaitItem()

            // Flow collection State
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                errorState.shouldBeNull()
                data.shouldBeEmpty()
                searchQuery.shouldNotBeEmpty()
            }

            // Loading State
            with(awaitItem()) {
                isLoading.shouldBeTrue()
                errorState.shouldBeNull()
                data.shouldBeEmpty()
            }

            // Then
            with(awaitItem()) {
                data.shouldNotBeEmpty()
                data.first().name shouldStartWith searchQuery
                data.first().id shouldBe 2
                data.size shouldBe 1
            }
        }
    }

    private fun createViewModel() {
        underTest = CharactersViewModel(
            savedStateHandle = SavedStateHandle.createHandle(null, null),
            repository = repository,
            errorHandler = errorHandler,
            dispatcherProvider = dispatcherProvider,
        )
    }
}

private val fakeCharactersPage = Page(
    listOf(
        Character(
            id = 1,
            name = "Iron Man",
            description = "Man made of iron!",
            thumbnail = Image(path = "", extension = ""),
            resourceURI = "",
            comics = ResourceItems(0, 0, "", emptyList()),
            series = ResourceItems(0, 0, "", emptyList()),
            stories = ResourceItems(0, 0, "", emptyList()),
            events = ResourceItems(0, 0, "", emptyList()),
            urls = emptyList(),
        ),
        Character(
            id = 2,
            name = "Spider Man",
            description = "Wall crawler!",
            thumbnail = Image(path = "", extension = ""),
            resourceURI = "",
            comics = ResourceItems(0, 0, "", emptyList()),
            series = ResourceItems(0, 0, "", emptyList()),
            stories = ResourceItems(0, 0, "", emptyList()),
            events = ResourceItems(0, 0, "", emptyList()),
            urls = emptyList(),
        ),
    ),
)
