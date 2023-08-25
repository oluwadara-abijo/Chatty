package com.fueled.chatty.core.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class InlineErrorStateButton(
    val text: String,
    val action: () -> Unit,
    val icon: ImageVector? = null,
)
