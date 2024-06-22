package drk.reza.myjetpackcomposecomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintLayoutPractice() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (button1, button2) = createRefs()
        createVerticalChain(button1, button2, chainStyle = ChainStyle.Packed)

        MyButton(
            "Button 1",
            modifier = Modifier.constrainAs(button1) {
                bottom.linkTo(button2.top)
                centerHorizontallyTo(parent)
                top.linkTo(parent.top)
//                width = Dimension.fillToConstraints
            }
        )
        MyButton("Button 2",
            modifier = Modifier.constrainAs(button2) {
                bottom.linkTo(parent.bottom)
                centerHorizontallyTo(parent)
                top.linkTo(button1.bottom)
            }
        )
    }
}

@Composable
fun AnotherCL1() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp)
    ) {

        val (box, profile) = createRefs()
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(2.dp, Color.Blue, RoundedCornerShape(10.dp))
                .background(Color.Cyan)
                .constrainAs(box) {
                    centerHorizontallyTo(parent)
                    centerVerticallyTo(parent)
                }
        )

        Box(modifier =
        Modifier
            .padding(start = 20.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(60.dp))
            .border(2.dp, Color.Gray,RoundedCornerShape(60.dp))
            .background(Color.White)
            .constrainAs(profile) {
                top.linkTo(box.bottom)
                bottom.linkTo(box.bottom)
//                centerHorizontallyTo(box)
            }
        )

    }

}

@Composable
fun MyButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = {/*TODO*/ }
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCL() {
//    ConstraintLayoutPractice()
    AnotherCL1()
}