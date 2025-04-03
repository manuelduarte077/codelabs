package dev.donmanuel.bmicalculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bmicalculator.composeapp.generated.resources.Res
import bmicalculator.composeapp.generated.resources.bmi
import org.jetbrains.compose.resources.stringResource

@Composable
fun BMIOverlayView(bmiDisplay: String) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White, shape = CircleShape)
            .padding(16.dp)
            .background(Color(0xFF313132), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(Res.string.bmi), color = Color.White, fontSize = 12.sp)
            Text(text = bmiDisplay, color = Color.White, fontSize = 48.sp)
        }
    }
}
