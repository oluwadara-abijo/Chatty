package com.fueled.chatty.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueled.chatty.core.ui.R

/**
 * This error state view is used when the error state is included in some layout
 * so not a dialog or snack etc. It can be furthermore enhanced with action button to retry etc
 */
@Composable
fun InlineErrorStateView(errorText: String, actionButton: InlineErrorStateButton? = null) {
    CenterColumn {
        Text(
            text = errorText,
            style = MaterialTheme.typography.bodyMedium,
        )
        if (actionButton != null) {
            Button(onClick = actionButton.action) {
                if (actionButton.icon != null) {
                    Icon(
                        imageVector = actionButton.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp),
                    )
                }
                Text(text = actionButton.text)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun InlineErrorStateView_Preview() {
    InlineErrorStateView(
        errorText = stringResource(id = R.string.common_error),
        actionButton = InlineErrorStateButton(
            text = stringResource(id = R.string.common_retry),
            action = {},
            icon = Icons.Outlined.Refresh,
        ),
    )
}
