package drk.reza.myjetpackcomposecomponents.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MySwitch(
    text: String,
    state: Boolean,
    onStateChange: (Boolean) -> Unit,
    fontWeight: FontWeight? = MaterialTheme.typography.titleSmall.fontWeight
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .selectable(
                selected = state,
                onClick = { onStateChange(!state) },
                role = Role.Switch
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = fontWeight
            ),
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f)
        )
        Switch(
            checked = state,
            onCheckedChange = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSwitch() {
    val (state, onStateChange3) = remember { mutableStateOf(false) }
    MySwitch(
        text = "Do what you Love",
        state = state,
        onStateChange = onStateChange3
    )
}