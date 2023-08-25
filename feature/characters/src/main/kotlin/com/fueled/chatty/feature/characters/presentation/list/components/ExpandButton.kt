package com.fueled.chatty.feature.characters.presentation.list.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.fueled.chatty.core.ui.R.drawable
import com.fueled.chatty.core.ui.components.buttons.HoveringIconButton

@Composable
internal fun ExpandButton(
    expandedState: Boolean,
    onClick: () -> Unit,
) {
    val rotationAngle by animateFloatAsState(targetValue = if (expandedState) DEGREES_180 else DEGREES_0)
    Box(modifier = Modifier.fillMaxSize()) {
        HoveringIconButton(
            modifier = Modifier
                .align(BottomCenter)
                .rotate(rotationAngle),
            targetValue = HOVER_ANIMATION_TARGET_VALUE,
            duration = HOVER_ANIMATION_DURATION,
            resId = drawable.ic_arrow_down,
            onClick = onClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpandButton_Preview(@PreviewParameter(ExpandedStateProvider::class) expandedState: Boolean) {
    ExpandButton(
        expandedState = expandedState,
        onClick = {},
    )
}

internal class ExpandedStateProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}

private const val HOVER_ANIMATION_TARGET_VALUE = 40f
private const val HOVER_ANIMATION_DURATION = 600
private const val DEGREES_180 = 180f
private const val DEGREES_0 = 0f
