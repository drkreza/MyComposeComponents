package drk.reza.myjetpackcomposecomponents.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MySlider(
    value: Float,
    onValueChanged: () -> Unit
) {
    Slider(value = value, onValueChange = { onValueChanged() })
}

@Preview(showBackground = true)
@Composable
fun PreviewSlider() {

    Column(modifier = Modifier.padding(10.dp)) {
        var sliderPosition by remember { mutableFloatStateOf(0f) }
        Text(text = String.format("%.2f", sliderPosition))
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })

        var stepSliderPosition by remember { mutableFloatStateOf(0f) }
        Text(text = String.format("%.2f", stepSliderPosition))

        Spacer(modifier = Modifier.height(10.dp))
        Slider(
            value = stepSliderPosition,
            onValueChange = { stepSliderPosition = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            steps = 5,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary
            )
        )
    }


}