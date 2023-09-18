package com.fueled.chatty.features.chats.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.fueled.chatty.core.ui.theme.Dimens.MessageWidthFraction
import com.fueled.chatty.core.ui.theme.Dimens.RadiusDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceFourth
import com.fueled.chatty.core.ui.theme.Dimens.SpaceHalf

@Composable
fun MessageColumn(
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
