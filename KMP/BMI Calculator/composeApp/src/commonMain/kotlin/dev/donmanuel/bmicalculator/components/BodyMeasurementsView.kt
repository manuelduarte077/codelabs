package dev.donmanuel.bmicalculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bmicalculator.composeapp.generated.resources.Res
import bmicalculator.composeapp.generated.resources.height
import bmicalculator.composeapp.generated.resources.weight
import dev.donmanuel.bmicalculator.presentation.CalculatorViewModel
import org.jetbrains.compose.resources.stringResource


@Composable
fun BodyMeasurementsView(
    modifier: Modifier = Modifier,
    heightCm: Float,
    heightDisplay: String,
    weightKg: Float,
    weightDisplay: String,
    onEvent: (CalculatorViewModel.UIEvent) -> Unit,
) {
    Column(
        modifier = modifier.background(Color.White).padding(all = 32.dp).padding(top = 100.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(Res.string.height), color = Color.Black, fontSize = 12.sp)
            Text(text = heightDisplay, color = Color.Black, fontSize = 12.sp)
        }

        MeasurementSliderView(
            min = 120f,
            max = 210f,
            steps = 90,
            sliderPosition = heightCm
        ) { height ->
            onEvent(CalculatorViewModel.UIEvent.ChangeHeight(height))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = stringResource(Res.string.weight), color = Color.Black, fontSize = 12.sp)
            Text(text = weightDisplay, color = Color.Black, fontSize = 12.sp)
        }

        MeasurementSliderView(
            min = 30f,
            max = 150f,
            steps = 240,
            sliderPosition = weightKg
        ) { weight ->
            onEvent(CalculatorViewModel.UIEvent.ChangeWeight(weight))
        }
    }
}
