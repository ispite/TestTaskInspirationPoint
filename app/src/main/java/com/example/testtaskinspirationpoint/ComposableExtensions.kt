package com.example.testtaskinspirationpoint

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(modifier: Modifier = Modifier, text: String, weight: Float) {
    Text(
        text = text,
        modifier
            .fillMaxHeight()
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun RowScope.StatelessEditableTableCell(
    modifier: Modifier = Modifier,
    message: String = "",
    enabled: Boolean = true,
    correctness: Boolean?,
    maxChar: Int = 1,
    textStyle: TextStyle,
    singleLine: Boolean = true,
    weight: Float,
    newValue: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(
        key1 = correctness,
    ) {
        if (correctness == true) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    BasicTextField(
        value = message,
        onValueChange = { newText -> if (newText.length <= maxChar) newValue(newText) },
        textStyle = textStyle,
        modifier =
        modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp),
        // internal implementation of the BasicTextField will dispatch focus events
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun RowScope.StatefulEditableTableCell(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    weight: Float,
    newValue: (Int?) -> Unit
) {
    var message by remember { mutableStateOf(text) }
    val correctness = if (message.isBlank()) null else "012345".contains(message)
    val textStyle =
        if (correctness == true) {
            newValue(message.toInt())
            LocalTextStyle.current.copy(color = Color.Black)
        } else {
            if (message.isBlank()) newValue(null)
            LocalTextStyle.current.copy(color = Color.Red)
        }

    if (correctness == false) LocalContext.current.customToast("Допустимы числа от 0 до 5")

    val maxChar = 1
    val singleLine = true

    StatelessEditableTableCell(
        modifier = modifier,
        message = message,
        enabled = enabled,
        correctness = correctness,
        maxChar = maxChar,
        textStyle = textStyle,
        singleLine = singleLine,
        weight = weight
    ) { message = it }
}