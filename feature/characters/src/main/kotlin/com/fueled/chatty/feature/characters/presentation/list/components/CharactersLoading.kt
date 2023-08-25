package com.fueled.chatty.feature.characters.presentation.list.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.components.CenterColumn

@Composable
internal fun CharactersLoading() {
    CenterColumn {
        CircularProgressIndicator()
        Text(text = stringResource(id = R.string.common_loading))
    }
}

@Preview
@Composable
private fun PreviewCharactersLoading() {
    CharactersLoading()
}
