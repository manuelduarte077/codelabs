package org.example.pickimagefromgallerykmp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.example.pickimagefromgallerykmp.gallery.rememberGalleryManager
import org.example.pickimagefromgallerykmp.model.Picture
import org.example.pickimagefromgallerykmp.style.ImageviewColors
import kotlin.math.absoluteValue

@Composable
fun GalleryScreen(modifier: Modifier = Modifier) {
    val pictures = remember { mutableStateListOf<Picture>() }
    val viewScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = { pictures.size }
    )

    val galleryManager = rememberGalleryManager { file ->
        pictures.add(0, Picture(file?.toImageBitmap()))
        viewScope.launch {
            pagerState.scrollToPage(0)
        }
    }

    fun selectPicture(index: Int) {
        viewScope.launch {
            pagerState.animateScrollToPage(
                page = index,
                animationSpec = tween(
                    easing = LinearOutSlowInEasing,
                    durationMillis = AnimationConstants.DefaultDurationMillis * 3
                )
            )
        }
    }

    Column(modifier = modifier.background(MaterialTheme.colors.background)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(393.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(state = pagerState) { index ->
                    val picture = pictures[index]
                    var image: ImageBitmap? by remember(picture) { mutableStateOf(null) }
                    LaunchedEffect(picture) { image = picture.imageBitmap }
                    image?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .animatePageChanges(pagerState, index)
                        ) {
                            Image(
                                bitmap = it,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (pictures.isEmpty()) {
                Text(
                    text = "Please upload new image",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.padding(top = 2.dp),
                    columns = GridCells.Adaptive(minSize = 130.dp),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    itemsIndexed(pictures) { index, picture ->
                        SquareImageContent(
                            picture = picture,
                            onClick = { selectPicture(index) },
                            isHighlighted = pagerState.targetPage == index
                        )
                    }
                }
            }

            CircularButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(36.dp),
                onClick = { galleryManager.launch() }
            )
        }
    }
}

@Composable
fun SquareImageContent(
    picture: Picture,
    isHighlighted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1F)
            .clickable(onClick = onClick)
    ) {
        SquareImage(
            modifier = Modifier.fillMaxSize(),
            picture = picture
        )

        val tween = tween<Float>(
            durationMillis = AnimationConstants.DefaultDurationMillis * 2,
            delayMillis = 100,
            easing = LinearOutSlowInEasing
        )

        AnimatedVisibility(visible = isHighlighted, enter = fadeIn(tween), exit = fadeOut(tween)) {
            Box(modifier = Modifier.fillMaxSize().background(ImageviewColors.uiLightBlack)) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp, bottom = 4.dp)
                        .clip(CircleShape)
                        .width(32.dp)
                        .background(ImageviewColors.uiLightBlack)
                        .aspectRatio(1F)
                        .clickable { onClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun CircularButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(ImageviewColors.uiLightBlack)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            tint = Color.White,
            imageVector = Icons.Filled.Add,
            contentDescription = null
        )
    }
}

private fun Modifier.animatePageChanges(pagerState: PagerState, index: Int) =
    graphicsLayer {
        val x = (pagerState.currentPage - index + pagerState.currentPageOffsetFraction)
        alpha = 1F - (x.absoluteValue * 0.7F).coerceIn(0F, 0.7F)
        val scale = 1F - (x.absoluteValue * 0.4F).coerceIn(0F, 0.4F)
        scaleX = scale
        scaleY = scale
        rotationY = x * 15F
    }