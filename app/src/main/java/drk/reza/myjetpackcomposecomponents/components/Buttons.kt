package drk.reza.myjetpackcomposecomponents.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyRoundedButton(
    modifier: Modifier = Modifier,
    height: Dp,
    backgroundColor: Color,
    fontSize: TextUnit = 16.sp,
    text: String,
    roundedCornerSize: Dp = 4.dp,
//    content: @Composable RowScope.() -> Unit,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(height),
        shape = RoundedCornerShape(roundedCornerSize),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        onClick = {
            onClick()
        }
    ) {
//        content()
        Text(
            text,
            fontSize = fontSize
        )
    }
}


@Composable
fun MyBorderedButton(
    modifier: Modifier = Modifier,
    text: String,
//    @DrawableRes startIcon: Int? = null,
//    @DrawableRes endIcon: Int? = null,
    height: Dp = ButtonDefaults.MinHeight,
    fontSize: TextUnit = 16.sp,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {

    OutlinedButton(
        modifier = modifier
            .height(height),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.background
        ),
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent
        ),
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}


@Composable
fun MyIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    contentDescription: String?,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    size: Dp = ButtonDefaults.MinHeight,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
//    val currentFocus = LocalFocusManager.current

    Button(
        modifier = modifier
            .size(size),
        shape = shape,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.White,
            containerColor = backgroundColor
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = {
//            currentFocus.clearFocus()

            onClick()
        }
    ) {
        Icon(
            modifier = Modifier.size(ButtonDefaults.IconSize),
            painter = painterResource(id = icon),
            contentDescription = contentDescription
        )
    }
}