package com.fueled.chatty.features.contacts.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens

@Composable
fun ContactDetailScreen(
    navigateUp: () -> Unit,
) {
    ContactDetailContent(
        viewModel = hiltViewModel(),
        navigateUp = navigateUp
    )
}

@Composable
private fun ContactDetailContent(
    viewModel: ContactDetailViewModel,
    navigateUp: () -> Unit,
) {
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(ContactDetailState.initialState())

    Screen {
        AppBarWidget(navigateUp = navigateUp)
        LazyColumn {
            item {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(model = state.contact?.picture ?: ""),
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(Dimens.ProfilePictureSize)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = state.contact?.name ?: "",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBarWidget(navigateUp: () -> Unit) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    )
}

object ContactDetailScreenArgs {
    const val EXTRA_CONTACT_ID = "id"

}
