package com.fueled.chatty.features.chats.presentation.detail.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import com.fueled.chatty.core.ui.theme.Dimens.RadiusDefault
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.feature.chats.R

@Composable
fun ChatDetailFooter(modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(SpaceDefault)
            .fillMaxWidth(),
        horizontalArrangement = SpaceEvenly,
        verticalAlignment = CenterVertically,
    ) {
        FooterIcon(iconId = R.drawable.ic_camera)
        FooterIcon(iconId = R.drawable.ic_image)
        TextField(
            modifier = Modifier
                .clip(RoundedCornerShape(RadiusDefault))
                .background(color = White),
            value = "",
            onValueChange = {},
            label = { Text(text = "Message") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
            ),
        )
    }
}

@Composable
private fun FooterIcon(@DrawableRes iconId: Int) {
    Icon(
        painter = painterResource(iconId),
        contentDescription = null,
        tint = colorScheme.primary,
    )
}
