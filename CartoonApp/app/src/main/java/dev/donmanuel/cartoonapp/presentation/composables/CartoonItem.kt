package dev.donmanuel.cartoonapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.donmanuel.cartoonapp.domain.model.Cartoon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartoonItem(
    cartoon: Cartoon,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .clickable { onClick() }
        .padding(8.dp)) {
        AsyncImage(
            model = cartoon.image,
            contentDescription = cartoon.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = cartoon.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp),
                fontSize = 16.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Rounded.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = cartoon.year.toString(), fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                cartoon.genre.forEach { genre ->
                    Text(
                        text = "$genre,", fontSize = 12.sp, modifier = Modifier.padding(end = 4.dp)
                    )
                }
            }

        }
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}