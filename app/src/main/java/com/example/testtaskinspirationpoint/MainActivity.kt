package com.example.testtaskinspirationpoint

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Greeting(name = "Android")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
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