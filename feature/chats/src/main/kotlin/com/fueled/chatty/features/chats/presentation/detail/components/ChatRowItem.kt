package com.fueled.chatty.features.chats.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.fueled.chatty.core.ui.theme.Dimens.MessageWidthFraction
import com.fueled.chatty.core.ui.theme.Dimens.ProfilePictureSizeSmall
import com.fueled.chatty.core.ui.theme.Dimens.RadiusDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceFourth
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
                        .size(ProfilePictureSizeSmall)
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

@Composable
private fun MessageColumn(
    backgroundColour: Color,
    textColor: Color,
    message: String,
    timestamp: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(MessageWidthFraction)
            .padding(vertical = SpaceFourth)
            .background(
                color = backgroundColour,
                shape = RoundedCornerShape(RadiusDefault),
            )
            .padding(horizontal = SpaceDefault, vertical = SpaceHalf),
    ) {
        Text(
            text = message,
            color = textColor,
            style = typography.bodyLarge,
            lineHeight = 18.sp,
        )
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = timestamp,
                color = textColor,
                style = typography.bodySmall,
            )
        }
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
