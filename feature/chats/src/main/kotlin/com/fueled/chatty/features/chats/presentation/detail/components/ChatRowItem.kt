package com.fueled.chatty.features.chats.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.fueled.chatty.core.ui.theme.Dimens.ProfilePictureSizeMedium
import com.fueled.chatty.core.ui.theme.Dimens.SpaceHalf
import com.fueled.chatty.features.chats.domain.model.ChatType.Sent
import com.fueled.chatty.features.chats.presentation.detail.model.ChatLogUiModel

@Composable
internal fun ChatRowItem(chat: ChatLogUiModel, senderPicture: String) {
    val isFromMe = chat.type == Sent

    val backgroundColour = if (isFromMe) {
        colorScheme.primary
    } else {
        colorScheme.surfaceVariant
    }

    val messageColour = if (isFromMe) {
        colorScheme.surface
    } else {
        colorScheme.onSurface
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start,
    ) {
        if (!isFromMe) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(CenterVertically),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = senderPicture),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .padding(end = SpaceHalf)
                        .size(ProfilePictureSizeMedium)
                        .clip(CircleShape),
                    contentScale = Crop,
                )
            }
        }
        MessageColumn(
            backgroundColour = backgroundColour,
            textColor = messageColour,
            message = chat.text,
            timestamp = chat.timeStamp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_ChatRowItem() {
    val chat = ChatLogUiModel(
        text = "How are you doing? I thought we were both gonna hsis wjwj wkkw wwk qqq qqq qqq www",
        timeStamp = "12:33 PM",
        type = Sent,
    )
    ChatRowItem(chat = chat, "")
}
