package com.fueled.chatty.features.contacts.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Search
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.fueled.chatty.core.ui.components.Screen
import com.fueled.chatty.core.ui.extensions.rememberFlowOnLifecycle
import com.fueled.chatty.core.ui.theme.Dimens.ProfilePictureLarge
import com.fueled.chatty.feature.contacts.R
import kotlin.random.Random

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
    val profilePicture = state.contact?.picture

    Screen {
        Column {
            AppBarWidget(navigateUp = navigateUp)
            LazyColumn(contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)) {
                item {
                    Column(Modifier.fillMaxWidth()) {
                        if (profilePicture.isNullOrEmpty()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_person_white),
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(ProfilePictureLarge)
                                    .clip(CircleShape)
                                    .background(color = Color.LightGray)
                                    .align(CenterHorizontally)
                            )
                        } else {
                            AsyncImage(
                                model = profilePicture,
                                placeholder = painterResource(id = R.drawable.ic_person),
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .size(ProfilePictureLarge)
                                    .clip(CircleShape)
                                    .align(CenterHorizontally)
                            )
                        }
                        Text(
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 24.dp),
                            text = state.contact?.name ?: "",
                            fontSize = 32.sp,
                        )
                        Text(
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .padding(top = 8.dp),
                            text = "+${randomPhoneNumber()}",
                            fontSize = 16.sp,
                        )
                        Spacer(Modifier.height(32.dp))
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ActionWidget(
                                icon = Icons.Outlined.Edit,
                                action = "Message"
                            )
                            ActionWidget(
                                icon = Icons.Outlined.Phone,
                                action = "Call"
                            )
                            ActionWidget(
                                icon = Icons.Outlined.Notifications,
                                action = "Mute"
                            )
                        }
                        Spacer(Modifier.height(32.dp))
                        Text(
                            "More actions",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        )
                        Spacer(Modifier.height(32.dp))
                        MoreActionsWidget(icon = Icons.Outlined.ExitToApp, action = "View media")
                        MoreActionsWidget(icon = Icons.Outlined.Search, action = "Search in conversations")
                        MoreActionsWidget(icon = Icons.Outlined.Notifications, action = "Notifications")
                    }
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

@Composable
fun ActionWidget(
    icon: ImageVector,
    action: String
) {
    Column(horizontalAlignment = CenterHorizontally) {
        Icon(
            imageVector = icon,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.background,
            contentDescription = action
        )
        Spacer(Modifier.height(8.dp))
        Text(action, color = MaterialTheme.colorScheme.primary, fontSize = 14.sp)

    }
}

@Composable
fun MoreActionsWidget(
    icon: ImageVector,
    action: String
) {
    Row(Modifier.padding(bottom = 32.dp)) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = action
        )
        Spacer(Modifier.width(16.dp))
        Text(action, fontSize = 16.sp)
    }
}


fun randomPhoneNumber(): String = Random.nextLong(10000000000, 99999999999).toString()

object ContactDetailScreenArgs {
    const val EXTRA_CONTACT_ID = "id"

}