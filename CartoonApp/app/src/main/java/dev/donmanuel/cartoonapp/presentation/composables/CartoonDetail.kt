package dev.donmanuel.cartoonapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.donmanuel.cartoonapp.domain.model.Cartoon

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CartoonDetail(cartoon: Cartoon) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = cartoon.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = cartoon.image,
            contentDescription = cartoon.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailItem(label = "Release Date", value = cartoon.year.toString())
            DetailItem(label = "Rating", value = cartoon.rating.toString())
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Creator:",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            cartoon.creator.forEach { creator ->
                DetailChip(text = creator)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Genres:",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            cartoon.genre.forEach { genre ->
                DetailChip(text = genre)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DetailItem(label = "Episodes", value = cartoon.episodes.toString())
            DetailItem(label = "Runtime", value = "${cartoon.runtime_in_minutes} min")
        }
    }

}

@Composable
fun DetailItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun DetailChip(text: String) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}