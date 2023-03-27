package com.example.testtaskinspirationpoint

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.testtaskinspirationpoint.ui.theme.TestTaskInspirationPointTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTaskInspirationPointTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp(modifier: Modifier = Modifier) {
//    LockScreenOrientation()
    Surface(modifier, color = MaterialTheme.colors.background) {
//        LockScreenOrientation()
        MainTable()
    }
}

@Composable
fun MainTable(modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        item {
/*            Row(modifier.background(Color.Gray)) {
                TableCell(text = "", weight = 0.3f)
                TableCell(text = "", weight = 0.1f)
                TableCell(text = "", weight = 0.1f)
            }*/
            CreateHead()
        }
    }
}

@Composable
fun CreateHead(modifier: Modifier = Modifier, count: Int = 7) {
    Row(
        modifier
            .background(Color.Gray)
            .height(IntrinsicSize.Min)
    ) {
        TableCell(modifier.fillMaxHeight(), text = "", weight = 0.3f)
        TableCell(modifier.fillMaxHeight(), text = "", weight = 0.1f)
        for (i in 1..count) {
            TableCell(modifier.fillMaxHeight(), text = "$i", weight = 0.1f)
        }
        TableCell(modifier.fillMaxHeight(), text = "Сумма очков", weight = 0.3f)
        TableCell(modifier.fillMaxHeight(), text = "Место", weight = 0.3f)
    }
}

@Preview(showBackground = true, widthDp = 640)
@Composable
fun DefaultPreview() {
    TestTaskInspirationPointTheme {
        MyApp()
    }
}

@Composable
fun LockScreenOrientation() {
    val context = LocalContext.current
    val activity = context.findActivity()
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}