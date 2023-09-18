package com.fueled.chatty.features.chats.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.background(color = colorScheme.background),
        title = {
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = SpaceDefault),
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            Icon(imageVector = Icons.Filled.Call, contentDescription = null)
            Spacer(modifier = Modifier.width(SpaceLarge))
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(SpaceLarge))
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorScheme.background),
    )
}
