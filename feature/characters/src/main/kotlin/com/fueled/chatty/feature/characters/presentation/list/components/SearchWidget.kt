package com.fueled.chatty.feature.characters.presentation.list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.fueled.chatty.core.ui.extensions.tag
import com.fueled.chatty.core.ui.theme.Dimens
import com.fueled.chatty.feature.characters.CharactersTag.SearchField
import com.fueled.chatty.feature.characters.R

private const val BORDER_COLOR_ALPHA = 0.4f

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SearchWidget(
    onQueryChanged: (String) -> Unit,
    query: String,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val emptyString = stringResource(R.string.empty_string)

    OutlinedTextField(
        textStyle = MaterialTheme.typography.body2,
        value = query,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = emptyString)
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onQueryChanged(emptyString)
                        keyboardController?.hide()
                    },
                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = emptyString)
                }
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = Color.Gray.copy(BORDER_COLOR_ALPHA),
        ),
        modifier = Modifier
            .tag(SearchField)
            .padding(Dimens.SpaceDefault)
            .fillMaxWidth(),

        placeholder = { Text(text = stringResource(R.string.search)) },
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        onValueChange = onQueryChanged,
    )
}
