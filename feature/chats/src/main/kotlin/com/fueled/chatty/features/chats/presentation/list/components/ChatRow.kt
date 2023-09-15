package com.fueled.chatty.features.chats.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.fueled.chatty.core.ui.extensions.clickable
import com.fueled.chatty.core.ui.theme.Dimens.ProfilePictureSize
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceFourth
import com.fueled.chatty.core.ui.theme.Dimens.SpaceTwoThirds
import com.fueled.chatty.features.chats.presentation.list.model.ChatUiModel

@Composable
fun ChatRow(
    chat: ChatUiModel,
    navigateToChatDetail: (Int) -> Unit,
) {
    // Checking this because not all recent chats have chat logs
    val chatHasDetails = chat.name == "Alex" || chat.name == "Bob"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = SpaceTwoThirds)
            .clickable { if (chatHasDetails) navigateToChatDetail(chat.friendId) },
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = chat.picture),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(ProfilePictureSize)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
        MessageColumn(
            friendName = chat.name,
            lastChat = chat.lastChat,
            timestamp = chat.timestamp,
        )
    }
}

@Composable
private fun RowScope.MessageColumn(
    friendName: String,
    lastChat: String,
    timestamp: String,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = SpaceDefault),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = friendName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.padding(SpaceFourth))
        Text(
            text = lastChat,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
    Text(
        text = timestamp,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

@Preview(showBackground = true)
@Composable
private fun ChatRowPreview() {
    val chat = ChatUiModel(
        name = "Bob",
        picture = "https://image.ibb.co/cA2oOb/alex_1.jpg",
        timestamp = "11:30 AM",
        lastChat = "Or maybe not, let me check logistics and call you. Give me sometime",
        friendId = 2,
    )

    ChatRow(chat) {}
}
