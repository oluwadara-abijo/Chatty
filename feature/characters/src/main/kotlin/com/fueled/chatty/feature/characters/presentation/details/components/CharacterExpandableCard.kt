package com.fueled.chatty.feature.characters.presentation.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.fueled.chatty.core.ui.R.string
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.components.TicketShape
import com.fueled.chatty.core.ui.theme.Dimens.CharacterCardCutoutRadius
import com.fueled.chatty.core.ui.theme.Dimens.ElevationSmall
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceFourth
import com.fueled.chatty.core.ui.theme.Dimens.SpaceHalf
import com.fueled.chatty.core.ui.theme.UiConstants.PERCENT_30
import com.fueled.chatty.core.ui.theme.UiConstants.PERCENT_50
import com.fueled.chatty.core.ui.theme.UiConstants.PERCENT_80
import com.fueled.chatty.feature.characters.presentation.list.components.ExpandButton
import com.fueled.chatty.feature.characters.presentation.model.AppearanceType.STORY
import com.fueled.chatty.feature.characters.presentation.model.AppearanceUiModel
import com.fueled.chatty.feature.characters.presentation.model.CharacterDetailsUiModel

@Composable
internal fun CharacterExpandableCard(
    modifier: Modifier = Modifier,
    character: CharacterDetailsUiModel,
) {
    var expandedState by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier
            .graphicsLayer {
                shadowElevation = ElevationSmall.toPx()
                shape = if (expandedState) {
                    RectangleShape
                } else {
                    TicketShape(CharacterCardCutoutRadius.toPx(), PERCENT_50)
                }
                clip = true
            },
    ) {
        Card(
            modifier = Modifier
                .animateContentSize()
                .fillMaxHeight(if (expandedState) 1f else PERCENT_30)
                .fillMaxWidth(if (expandedState) 1f else PERCENT_80),
        ) {
            if (expandedState) {
                ExpandedCharacterContentCard(character = character)
            } else {
                ClosedCharacterContentCard(description = character.description)
            }
            ExpandButton(
                expandedState = expandedState,
                onClick = { expandedState = !expandedState },
            )
        }
    }
}

@Composable
private fun ClosedCharacterContentCard(description: String) {
    var styledDescription by remember { mutableStateOf(buildAnnotatedString { append(description) }) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceDefault),
    ) {
        Text(
            text = styledDescription,
            onTextLayout = { layoutResult ->
                if (layoutResult.hasVisualOverflow) {
                    val lastLine = layoutResult.lineCount - 1
                    val start = layoutResult.getLineStart(lastLine)
                    val end = layoutResult.getLineEnd(lastLine)
                    val firstPart = description.substring(0, start)
                    val lastPart = description.substring(start, end)
                    styledDescription = buildAnnotatedString {
                        append(firstPart)
                        withStyle(
                            SpanStyle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Gray, Color.Gray),
                                ),
                            ),
                        ) {
                            append(lastPart)
                        }
                    }
                }
            },
        )
    }
}

@Composable
private fun ExpandedCharacterContentCard(character: CharacterDetailsUiModel) {
    Column(
        modifier = Modifier
            .padding(
                start = SpaceDefault,
                end = SpaceDefault,
                bottom = SpaceDefault,
            )
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
    ) {
        CharacterInfo(
            imageUrl = character.imageUrl,
            name = character.name,
            description = character.description,
        )
        Spacer(modifier = Modifier.height(SpaceHalf))
        AppearancesWidget(
            stories = character.stories,
            comics = character.comics,
            events = character.events,
            series = character.series,
        )
    }
}

@Composable
private fun CharacterInfo(imageUrl: String, name: String, description: String) {
    Column {
        Text(text = name, fontWeight = Bold)
        Spacer(modifier = Modifier.height(SpaceFourth))
        Text(
            text = buildAnnotatedString {
                appendInlineContent(id = INLINE_CONTENT_ID)
                append(description)
            },
            inlineContent = getDescriptionInlineContent(imageUrl = imageUrl, name = name),
        )
    }
}

private fun getDescriptionInlineContent(imageUrl: String, name: String) = mapOf(
    INLINE_CONTENT_ID to InlineTextContent(
        Placeholder(
            INLINE_CONTENT_SIZE.sp,
            INLINE_CONTENT_SIZE.sp,
            PlaceholderVerticalAlign.TextBottom,
        ),
    ) {
        Image(
            modifier = Modifier
                .size(INLINE_CONTENT_SIZE.dp)
                .clip(CircleShape),
            painter = rememberImagePainter(imageUrl),
            contentDescription = stringResource(id = string.character_details_image_desc, name),
            alignment = Center,
            contentScale = Crop,
        )
    },
)

@Preview
@Composable
private fun CharacterExpandableCard_Preview() {
    val testAppearance = AppearanceUiModel(1, listOf("Preview"), STORY)
    Screen {
        CharacterExpandableCard(
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

private const val INLINE_CONTENT_ID = "hero_image"
private const val INLINE_CONTENT_SIZE = 100
