package com.fueled.chatty.feature.characters.presentation.list.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.components.CenterColumn

@Composable
internal fun CharactersEmpty() {
    CenterColumn {
        Text(text = stringResource(id = R.string.common_empty))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharactersEmpty() {
    CharactersEmpty()
}
