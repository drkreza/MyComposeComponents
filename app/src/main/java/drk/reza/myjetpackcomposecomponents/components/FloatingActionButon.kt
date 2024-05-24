package drk.reza.myjetpackcomposecomponents.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyFloatingActionButton(
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    FloatingActionButton(onClick = { onClick() }) {
        content()
    }
}

@Composable
fun MyExtendedFloatingActionButton(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        icon = icon,
        text = text,
        onClick = { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
fun FloatingActionButtonPreview() {
    Column(modifier = Modifier.padding(10.dp)) {
        MyFloatingActionButton(
            {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            }
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
        MyExtendedFloatingActionButton(
            { Icon(Icons.Filled.Favorite, contentDescription = null) },
            { Text("ADD TO BASKET") }
        ) {

        }
    }
}