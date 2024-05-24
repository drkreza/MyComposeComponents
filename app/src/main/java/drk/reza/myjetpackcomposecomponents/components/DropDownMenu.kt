package drk.reza.myjetpackcomposecomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import drk.reza.myjetpackcomposecomponents.R

@Composable
fun <E> MyDropDownSpinner(
    modifier: Modifier = Modifier,
    defaultText: String = "Select...",
    selectedItem: E,
    onItemSelected: (Int, E) -> Unit,
    itemList: List<E>?
) {
    var isOpen by remember { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .height(56.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (selectedItem == null || selectedItem.toString().isEmpty()) {
            Text(
                text = defaultText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 3.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(.45f)
            )
        }

        Text(
            text = selectedItem?.toString() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 32.dp, bottom = 3.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(.85f),
            expanded = isOpen,
            onDismissRequest = {
                isOpen = false
            }
        ) {
            itemList?.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(item.toString())
                    },
                    onClick = {
                        isOpen = false
                        onItemSelected(index, item)
                    }
                )
            }
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(24.dp),
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "Dropdown"
        )

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .clickable(
                    onClick = { isOpen = true }
                )
        )
    }
}

@Preview
@Composable
fun DropDownMenuPreview() {
    val countryList = listOf(
        "Bangladesh",
        "Pakistan",
        "Palestine",
        "Malaysia"
    )
    var selectedItem1 by remember { mutableStateOf("") }
    var selectedItem2 by remember { mutableStateOf("Bangladesh") }

    MyDropDownSpinner(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        defaultText = "Select Country...",
        selectedItem = selectedItem1, // selectedItem = selectedItem2,
        onItemSelected = { index, item ->
            selectedItem1 = item
//            selectedItem2 = item
        },
        itemList = countryList
    )
}