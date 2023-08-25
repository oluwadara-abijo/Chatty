package com.fueled.chatty.feature.characters.presentation.list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.theme.Dimens
import com.fueled.chatty.feature.characters.presentation.model.CharacterUiModel

@Composable
internal fun CharactersContent(
    data: List<CharacterUiModel>,
    onCharacterSelected: (Long) -> Unit,
) {
    Screen {
        Row(
            Modifier
                .fillMaxHeight()
                .wrapContentSize(Alignment.TopStart),
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = Dimens.SpaceDefault,
                    end = Dimens.SpaceDefault,
                    bottom = Dimens.SpaceDefault,
                ),
            ) {
                items(data) { item ->
                    CharacterCard(
                        data = item,
                        onCharacterSelected = onCharacterSelected,
                    )
                }
            }
        }
    }
}
