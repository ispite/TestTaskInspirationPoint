package com.example.testtaskinspirationpoint

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val message = remember { mutableStateOf("") }

    val enabled = true
    val singleLine = true

    BasicTextField(
        value = message.value,
        onValueChange = { newText -> message.value = newText },
        modifier = modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(start = 8.dp, top = 9.5.dp, bottom = 9.dp, end = 8.dp),
        // internal implementation of the BasicTextField will dispatch focus events
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )
}