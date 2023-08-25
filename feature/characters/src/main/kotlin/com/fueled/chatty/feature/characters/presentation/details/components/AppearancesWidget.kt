package com.fueled.chatty.feature.characters.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueled.chatty.core.ui.R.string
import com.fueled.chatty.core.ui.theme.Dimens.CharacterAppearanceHeight
import com.fueled.chatty.core.ui.theme.Dimens.CharacterAppearanceWidth
import com.fueled.chatty.core.ui.theme.Dimens.ElevationTwo
import com.fueled.chatty.core.ui.theme.Dimens.RadiusMedium
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceHalf
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.COMIC
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.EVENT
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.SERIES
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.STORY
import com.fueled.chatty.feature.characters.presentation.model.AppearanceUiModel

@Composable
internal fun AppearancesWidget(
    stories: AppearanceUiModel,
    comics: AppearanceUiModel,
    events: AppearanceUiModel,
    series: AppearanceUiModel,
) {
    listOf(stories, comics, events, series)
        .filter { it.items.isNotEmpty() }
        .forEach { appearance ->
            AppearanceColumn(item = appearance)
        }
}

@Composable
private fun AppearanceColumn(item: AppearanceUiModel) {
    Column(modifier = Modifier.padding(bottom = SpaceDefault)) {
        Text(text = item.type.toText(item.available))
        Spacer(modifier = Modifier.padding(bottom = SpaceDefault))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(SpaceHalf),
        ) {
            items(item.items) {
                Card(
                    modifier = Modifier
                        .width(CharacterAppearanceWidth)
                        .height(CharacterAppearanceHeight),
                    shape = RoundedCornerShape(RadiusMedium),
                    elevation = ElevationTwo,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun AppearanceType.toText(amount: Int): String {
    return when (this) {
        STORY -> stringResource(id = string.character_details_stories, amount)
        EVENT -> stringResource(id = string.character_details_events, amount)
        COMIC -> stringResource(id = string.character_details_comics, amount)
        SERIES -> stringResource(id = string.character_details_series, amount)
    }
}

@Preview
@Composable
private fun AppearanceColumn_Preview() {
    AppearanceColumn(
        item = AppearanceUiModel(
            3,
            listOf("Appearance", "Preview", "Items"),
            STORY,
        ),
    )
}
