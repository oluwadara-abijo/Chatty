package com.fueled.chatty.core.ui.extensions

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import com.fueled.chatty.core.ui.util.UiTag

@Composable
fun Modifier.clickable(onClick: (() -> Unit)? = null): Modifier {
    return onClick?.let { block ->
        clickable(onClick = block)
    } ?: this
}

fun Modifier.tag(tag: UiTag): Modifier = semantics { testTag = tag.key }
