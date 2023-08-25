package com.fueled.chatty.core.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.R.drawable
import kotlin.math.roundToInt

@Composable
fun HoveringIconButton(
    modifier: Modifier = Modifier,
    initialValue: Float = 0f,
    targetValue: Float,
    duration: Int,
    @DrawableRes resId: Int,
    onClick: () -> Unit,
) {
    val infTransition = rememberInfiniteTransition()
    val offsetY by infTransition.animateFloat(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = duration, easing = LinearEasing),
            repeatMode = Reverse,
        ),
    )
    IconButton(
        modifier = modifier.offset { IntOffset(x = 0, y = offsetY.roundToInt()) },
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = stringResource(id = R.string.character_details_expand_desc),
        )
    }
}

@Preview
@Composable
/** Enable interactive mode to preview animation */
private fun HoveringIconButton_Preview() {
    HoveringIconButton(
        targetValue = 40f,
        duration = 600,
        resId = drawable.ic_arrow_down,
        onClick = { },
    )
}
