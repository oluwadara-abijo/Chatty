package com.fueled.chatty.feature.characters.presentation.details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.fueled.chatty.core.testing.BaseTest
import com.fueled.chatty.core.ui.util.ErrorHandler
import com.fueled.chatty.feature.characters.domain.CharacterRepository
import com.fueled.chatty.feature.characters.domain.model.Character
import com.fueled.chatty.feature.characters.domain.model.Image
import com.fueled.chatty.feature.characters.domain.model.ResourceItems
import com.fueled.chatty.feature.characters.presentation.list.toDetailsUiModel
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CharacterDetailsViewModelTest : BaseTest<CharacterDetailsViewModel>() {

    @RelaxedMockK
    private lateinit var repository: CharacterRepository

    @RelaxedMockK
    private lateinit var errorHandler: ErrorHandler
    override lateinit var underTest: CharacterDetailsViewModel

    @Test
    fun `Given repository call succeeds when screen is loaded then character details are displayed`() =
        startTest {
            // Given
            val fakeFlow = flow {
                delay(500) // Imitates request of API or DB call being made
                emit(fakeCharacter)
            }
            coEvery { repository.getCharacter(fakeCharacterId) } returns fakeFlow

            createViewModel(
                bundle = mapOf(Pair(CharacterDetailScreenArgs.EXTRA_ID, fakeCharacterId)),
            )

            // Then
            underTest.state.test {
                // Init State
                with(awaitItem()) {
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    character.shouldBeNull()
                }
                // Loading State
                with(awaitItem()) {
                    isLoading.shouldBeTrue()
                    errorState.shouldBeNull()
                    character.shouldBeNull()
                }

                // Data State
                with(awaitItem()) {
                    isLoading.shouldBeFalse()
                    errorState.shouldBeNull()
                    character.shouldBe(fakeCharacterUiModel)
                }
            }
        }

    @Test
    fun `Given repository call fails when screen is loaded then error is displayed`() = startTest {
        // Given
        val fakeFlow = flow<Character> {
            delay(500) // Imitates request of API or DB call being made
            throw IllegalStateException()
        }
        coEvery { repository.getCharacter(fakeCharacterId) } returns fakeFlow

        createViewModel(
            bundle = mapOf(Pair(CharacterDetailScreenArgs.EXTRA_ID, fakeCharacterId)),
        )
        // Then
        underTest.state.test {
            // Init State
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                errorState.shouldBeNull()
                character.shouldBeNull()
            }
            // Loading State
            with(awaitItem()) {
                isLoading.shouldBeTrue()
                errorState.shouldBeNull()
                character.shouldBeNull()
            }

            // Data State
            with(awaitItem()) {
                isLoading.shouldBeFalse()
                errorState.shouldNotBeNull()
                character.shouldBeNull()
            }
        }
    }

    @Test
    fun `given a view action is invoked, the event is ignored`() = startTest {
        // Given
        val fakeFlow = flow {
            delay(500) // Imitates request of API or DB call being made
            emit(fakeCharacter)
        }
        coEvery { repository.getCharacter(fakeCharacterId) } returns fakeFlow

        createViewModel(
            bundle = mapOf(Pair(CharacterDetailScreenArgs.EXTRA_ID, fakeCharacterId)),
        )

        underTest.state.test {
            awaitItem() // Init State
            awaitItem() // Loading State
            awaitItem() // Error State
            underTest.events.test {
                underTest.onViewAction(Unit)
                expectNoEvents()
            }
            expectNoEvents()
        }
    }

    @Test
    fun `given saved state bundle contains no property, and exception is thrown`() = startTest {
        shouldThrow<IllegalStateException> {
            createViewModel(
                bundle = emptyMap(),
            )
        }
    }

    private fun createViewModel(
        bundle: Map<String, Any>,
    ) {
        underTest = CharacterDetailsViewModel(
            savedStateHandle = SavedStateHandle(bundle),
            characterRepository = repository,
            errorHandler = errorHandler,
            dispatcherProvider = dispatcherProvider,
        )
    }
}

private const val fakeCharacterId = 1L
private val fakeCharacter = Character(
    id = fakeCharacterId,
    name = "Iron Man",
    description = "Man made of iron!",
    thumbnail = Image(path = "", extension = ""),
    resourceURI = "",
    comics = ResourceItems(0, 0, "", emptyList()),
    series = ResourceItems(0, 0, "", emptyList()),
    stories = ResourceItems(0, 0, "", emptyList()),
    events = ResourceItems(0, 0, "", emptyList()),
    urls = emptyList(),
)
private val fakeCharacterUiModel = fakeCharacter.toDetailsUiModel()
