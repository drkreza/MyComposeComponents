@file:OptIn(ExperimentalFoundationApi::class)

package drk.reza.myjetpackcomposecomponents.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import drk.reza.myjetpackcomposecomponents.R
import drk.reza.myjetpackcomposecomponents.ui.theme.TailwindCSSColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview(showBackground = true)
fun MyOutlinedTextFieldSample() {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}

@Composable
@Preview(showBackground = true)
fun TextFieldWithCutCopyPasteDisabled() {
    var textValue by remember { mutableStateOf(TextFieldValue()) }

    CompositionLocalProvider(LocalTextToolbar provides EmptyTextToolbar) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textValue,
            onValueChange = { newValue ->
                textValue = if (newValue.selection.length > 0) {
                    newValue.copy(selection = textValue.selection)
                } else {
                    newValue
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun TextInputFieldTwo(
    modifier: Modifier = Modifier,
    textFieldValue: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) },
    background: Color = TailwindCSSColor.Gray100,
    shape: Shape = MaterialTheme.shapes.medium,
    placeholder: String = "This is a place holder",
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions? = null,
    height: Dp = 56.dp,
    @DrawableRes icon: Int? = null,
    onValueChange: (TextFieldValue) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val interactionSourceState = interactionSource.collectIsFocusedAsState()
    val scope = rememberCoroutineScope()
    val isImeVisible = WindowInsets.isImeVisible

    // Bring the composable into view (visible to user).
    LaunchedEffect(isImeVisible, interactionSourceState.value) {
        if (isImeVisible && interactionSourceState.value) {
            scope.launch {
                delay(300)
                bringIntoViewRequester.bringIntoView()
            }
        }
    }

    val focusRequester = FocusRequester()
    val isFocused = remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier
            .focusRequester(focusRequester)
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusChanged {
                isFocused.value = it.isFocused
            }
            .fillMaxWidth(),
        interactionSource = interactionSource,
        value = textFieldValue.value,
        singleLine = singleLine,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
            fontWeight = FontWeight.Medium,
            color = TailwindCSSColor.Gray900
        ),
        onValueChange = {
            if (it.text.length <= maxLength) {
                textFieldValue.value = it

                onValueChange(it)
            }
        },
        keyboardActions = keyboardActions ?: KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onSearch = { focusManager.clearFocus() }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        readOnly = readOnly,
        decorationBox = { innerTextField ->
            Box(
                Modifier
                    .clip(shape)
                    .background(background)
                    .height(height)
            ) {
                Row(
                    Modifier.fillMaxSize()
                ) {
                    icon?.let {
                        Image(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(15.dp),
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(TailwindCSSColor.Gray900)
                        )
                    }

                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(
                                start = if (icon == null) 15.dp else 0.dp,
                                bottom = 0.dp,
                                end = 15.dp
                            )
                    ) {
                        val hasText = textFieldValue.value.text.isNotEmpty()

                        val animPlaceholder: Dp by animateDpAsState(
                            if (isFocused.value || hasText) 6.dp else 14.dp
                        )
                        val animPlaceHolderFontSize: Int by animateIntAsState(
                            if (isFocused.value || hasText) 12 else 14
                        )

                        Text(
                            modifier = Modifier
                                .graphicsLayer {
                                    translationY = animPlaceholder.toPx()
                                },
                            text = placeholder,
                            color = TailwindCSSColor.Gray900.copy(alpha = .35f),
                            fontSize = animPlaceHolderFontSize.sp,
                            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                            maxLines = if (singleLine) 1 else Int.MAX_VALUE,
                            overflow = TextOverflow.Ellipsis
                        )

                        Box(
                            Modifier
                                .padding(top = 21.dp)
                                .fillMaxWidth()
                        ) {
                            innerTextField()
                        }
                    }
                }
            }
        }
    )
}

@ExperimentalFoundationApi
@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview(showBackground = true)
fun PasswordInputFieldOne(
    modifier: Modifier = Modifier,
    textFieldValue: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) },
    placeholder: String = "●●●●●●",
    readOnly: Boolean = false,
    fontSize: TextUnit = 16.sp,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions? = null,
    height: Dp = 56.dp,
    isError: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    var passwordVisibility by remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val interactionSourceState = interactionSource.collectIsFocusedAsState()
    val scope = rememberCoroutineScope()
    val isImeVisible = WindowInsets.isImeVisible

    // Bring the composable into view (visible to user).
    LaunchedEffect(isImeVisible, interactionSourceState.value) {
        if (isImeVisible && interactionSourceState.value) {
            scope.launch {
                delay(300)
                bringIntoViewRequester.bringIntoView()
            }
        }
    }

    BasicTextField(
        value = textFieldValue.value,
        singleLine = true,
        visualTransformation =
        if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation(mask = '●')
        },
        onValueChange = {
            textFieldValue.value = it

            onValueChange(it)
        },
        keyboardActions = keyboardActions ?: KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = imeAction
        ),
        interactionSource = interactionSource,
        modifier = modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .fillMaxWidth(),
        readOnly = readOnly,
        textStyle = TextStyle(
            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
            fontSize = fontSize,
            color = TailwindCSSColor.Gray900
        ),
        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        if (isError) TailwindCSSColor.Red500.copy(.1f) else TailwindCSSColor.Gray100
                    )
                    .height(height),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .padding(start = 12.dp, bottom = 2.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()

                    if (textFieldValue.value.text.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(bottom = 2.dp),
                            text = placeholder,
                            color = TailwindCSSColor.Gray900.copy(.35f),
                            fontSize = fontSize
                        )
                    }
                }

                Spacer(Modifier.width(16.dp))

                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    AnimatedVisibility(
                        visible = passwordVisibility,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_hide),
                            "Show Password"
                        )
                    }

                    AnimatedVisibility(
                        visible = !passwordVisibility,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_show),
                            "Hide Password"
                        )
                    }
                }
            }
        }
    )
}


object EmptyTextToolbar : TextToolbar {
    override val status: TextToolbarStatus = TextToolbarStatus.Hidden
    override fun hide() {}
    override fun showMenu(
        rect: Rect,
        onCopyRequested: (() -> Unit)?,
        onPasteRequested: (() -> Unit)?,
        onCutRequested: (() -> Unit)?,
        onSelectAllRequested: (() -> Unit)?
    ) {
    }
}