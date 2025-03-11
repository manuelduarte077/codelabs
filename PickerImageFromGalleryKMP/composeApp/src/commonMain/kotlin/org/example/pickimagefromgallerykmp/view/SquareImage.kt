package org.example.pickimagefromgallerykmp.view

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import org.example.pickimagefromgallerykmp.model.Picture

@Composable
fun SquareImage(
    modifier: Modifier = Modifier,
    picture: Picture,
    filter: (ImageBitmap) -> ImageBitmap = remember { { it } }
) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(picture) {
        imageBitmap = picture.imageBitmap
    }

    imageBitmap?.let {
        Image(
            bitmap = filter(it),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }
}