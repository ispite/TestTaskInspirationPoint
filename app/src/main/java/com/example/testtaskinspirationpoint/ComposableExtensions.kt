package com.example.testtaskinspirationpoint

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(modifier: Modifier = Modifier, text: String, weight: Float) {
    Text(
        text = text,
        modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}