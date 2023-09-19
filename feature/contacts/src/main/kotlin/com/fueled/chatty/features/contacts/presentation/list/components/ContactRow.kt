package com.fueled.chatty.features.contacts.presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.fueled.chatty.core.ui.extensions.clickable
import com.fueled.chatty.core.ui.theme.Dimens.ProfilePictureSize
import com.fueled.chatty.core.ui.theme.Dimens.SpaceDefault
import com.fueled.chatty.feature.contacts.R
import com.fueled.chatty.features.contacts.presentation.list.model.ContactUiModel

@Composable
fun ContactRow(
    contact: ContactUiModel,
    navigateToContactDetail: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceDefault)
            .clickable { navigateToContactDetail(contact.id) },
        verticalAlignment = CenterVertically,
    ) {
        if (contact.picture.isEmpty()) {
            Image(
                painterResource(id = R.drawable.ic_person),
                contentDescription = stringResource(R.string.profile_picture),
                modifier = Modifier
                    .size(ProfilePictureSize)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface),
            )
        } else {
            Image(
                painter = rememberAsyncImagePainter(model = contact.picture),
                contentDescription = stringResource(R.string.profile_picture),
                modifier = Modifier
                    .size(ProfilePictureSize)
                    .clip(CircleShape),
                contentScale = Crop,
            )
        }
        Spacer(modifier = Modifier.width(SpaceDefault))
        Text(text = contact.name)
    }
}
