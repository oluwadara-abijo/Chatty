package com.fueled.chatty.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fueled.chatty.core.ui.R
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWidget(searchTerm: String) {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceDefault),
        query = searchTerm,
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = { Text(text = stringResource(R.string.search)) },
        leadingIcon =
        { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
    ) {
    }
}
