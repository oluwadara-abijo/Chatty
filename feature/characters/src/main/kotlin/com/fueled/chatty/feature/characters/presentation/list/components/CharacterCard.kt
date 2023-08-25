package com.fueled.chatty.feature.characters.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.fueled.chatty.core.ui.extensions.tag
import com.fueled.chatty.core.ui.theme.Dimens
import com.fueled.chatty.feature.characters.CharactersTag.CharacterCard
import com.fueled.chatty.feature.characters.presentation.model.CharacterUiModel

@Composable
internal fun CharacterCard(
    modifier: Modifier = Modifier,
    data: CharacterUiModel,
    onCharacterSelected: (Long) -> Unit = { },
) {
    Card(
        modifier = modifier
            .tag(CharacterCard)
            .padding(Dimens.SpaceTwoThirds)
            .fillMaxWidth(fraction = 0.5F)
            .aspectRatio(1F, false)
            .clickable { onCharacterSelected(data.id) },
        shape = RoundedCornerShape(Dimens.RadiusMedium),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.BottomStart,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(data.imageUrl),
                contentDescription = "Character Image",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = Dimens.RadiusMedium))
                    .background(MaterialTheme.colors.primary),
            ) {
                Text(
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onPrimary),
                    modifier = Modifier.padding(
                        vertical = Dimens.SpaceFourth,
                        horizontal = Dimens.SpaceHalf,
                    ),
                    text = data.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharacterCard_Preview() {
    CharacterCard(
        data = CharacterUiModel(
            id = 1011334,
            name = "3-D Man",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/standard_fantastic.jpg",
        ),
    )
}
