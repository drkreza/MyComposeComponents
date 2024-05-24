package drk.reza.myjetpackcomposecomponents.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> MyRadioButtonGroup(
    modifier: Modifier = Modifier,
    text: String,
    value: T,
    selectedOption: T,
    onOptionSelected: (T) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectable(
                selected = (value == selectedOption),
                onClick = { onOptionSelected(value) },
                role = Role.RadioButton
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = (value == selectedOption),
            onClick = null // null recommended for accessibility with screenreaders
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.merge(),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonPreview() {
    val radioOptions = listOf("Happiness" to 1, "Money" to 2, "Both" to 3)
    val (selectedOption, onOptionSelected) = remember { mutableIntStateOf(radioOptions[0].second) }
    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            MyRadioButtonGroup(
                text = text.first,
                value = text.second,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected
            )
        }
    }
}
