package com.example.testtaskinspirationpoint

import android.util.Log
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
fun RowScope.EditableTableCell(modifier: Modifier = Modifier, text: String = "", weight: Float) {
    var message by remember { mutableStateOf("") }
    val correctness = if (message.isBlank()) null else "012345".contains(message)
    val myTextStyle =
        if (correctness == true) LocalTextStyle.current.copy(color = Color.Black)
        else LocalTextStyle.current.copy(color = Color.Red)

    if (correctness == false) LocalContext.current.customToast("Допустимы числа от 0 до 5")
    Log.d("EditableTableCell", "EditableTableCell: $correctness")

    val maxChar = 1
    val enabled = true
    val singleLine = true
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
        onValueChange = { newText -> if (newText.length <= maxChar) message = newText },
        textStyle = myTextStyle,
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