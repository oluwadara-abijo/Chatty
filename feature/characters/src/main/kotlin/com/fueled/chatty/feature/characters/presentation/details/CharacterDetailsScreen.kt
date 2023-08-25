package com.fueled.chatty.feature.characters.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.UiConstants.PERCENT_50
import com.fueled.chatty.feature.characters.presentation.details.components.CharacterExpandableCard
import com.fueled.chatty.feature.characters.presentation.details.contract.CharacterDetailsState
import com.fueled.chatty.feature.characters.presentation.list.components.CharactersLoading
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.STORY
import com.fueled.chatty.feature.characters.presentation.model.AppearanceUiModel
import com.fueled.chatty.feature.characters.presentation.model.CharacterDetailsUiModel

object CharacterDetailScreenArgs {
    const val EXTRA_ID = "id"
}

@Composable
fun CharacterDetailsScreen(setToolbarTitle: (String) -> Unit) {
    CharacterDetailsScreenContent(
        viewModel = hiltViewModel(),
        setToolbarTitle = setToolbarTitle,
    )
}

@Composable
private fun CharacterDetailsScreenContent(
    viewModel: CharacterDetailsViewModel,
    setToolbarTitle: (String) -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterDetailsState.initialState)

    Screen {
        if (state.isLoading) {
            CharactersLoading()
        }

        state.character?.run {
            setToolbarTitle(name)
            CharacterDetailContent(
                modifier = Modifier.align(Center),
                character = this,
            )
        }
    }
}

@Composable
private fun CharacterDetailContent(
    modifier: Modifier,
    character: CharacterDetailsUiModel,
) {
    CharacterImage(
        name = character.name,
        url = character.imageUrl,
    )
    CharacterExpandableCard(
        modifier = modifier,
        character = character,
    )
}

@Composable
private fun CharacterImage(
    name: String,
    url: String,
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(PERCENT_50),
        painter = rememberImagePainter(url),
        contentDescription = stringResource(id = R.string.character_details_image_desc, name),
        alignment = Center,
        contentScale = Crop,
    )
}

@Preview(showSystemUi = true)
@Composable
private fun CharacterDetailContent_Preview() {
    val testAppearance = AppearanceUiModel(1, listOf("Preview"), STORY)
    Screen {
        CharacterDetailContent(
            modifier = Modifier.align(Center),
            character = CharacterDetailsUiModel(
                "Preview",
                "Preview description",
                "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                comics = testAppearance,
                series = testAppearance,
                stories = testAppearance,
                events = testAppearance,
            ),
        )
    }
}
