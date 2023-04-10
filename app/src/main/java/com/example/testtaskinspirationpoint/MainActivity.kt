package com.example.testtaskinspirationpoint

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testtaskinspirationpoint.data.Config
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
    Surface(modifier, color = MaterialTheme.colors.background) {
        LockScreenOrientation()
        MainTable()
    }
}

@Composable
fun MainTable(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel()
) {
    val list = mainViewModel.participants
    LazyColumn(modifier) {
        item {
            CreateHead(count = Config.COUNT)
        }
        items(list, key = { participant -> participant.id }) { it ->
            CreateRow(
                ordinal = it.id,
                count = list.size,
                pointsTotal = it.pointsTotal,
                place = it.place,
                onCompetitionChange = { participantId, competitionId, competitionResult ->
                    mainViewModel.changeParticipantCompetitionResult(
                        participantId,
                        competitionId,
                        competitionResult
                    )
                })
        }
    }
}

@Composable
fun CreateRow(
    modifier: Modifier = Modifier,
    ordinal: Int,
    count: Int,
    pointsTotal: Int?,
    place: Int?,
    onCompetitionChange: (Int, Int, Int?) -> Unit
) {
    val newOrdinal = ordinal + 1
    Row(
        modifier
    ) {
        TableCell(modifier, text = "Участник $newOrdinal", weight = 0.3f)
        TableCell(modifier, text = "$newOrdinal", weight = 0.1f)
        for (i in 0 until count) {
            if (i == ordinal) {
                StatefulEditableTableCell(
                    modifier.background(Color.Black),
                    text = "",
                    enabled = false,
                    weight = 0.1f,
                    newValue = {})
                Log.d("CreateRow", "BLACK CELL number: $i")
                onCompetitionChange(ordinal, i, null)
            } else StatefulEditableTableCell(modifier, text = "", weight = 0.1f) {
                Log.d("CreateRow", "EDITABLE CELL number: $i")
                onCompetitionChange(ordinal, i, it)
            }
        }
        TableCell(modifier, text = pointsTotal?.toString() ?: "", weight = 0.3f)
        TableCell(modifier, text = place?.toString() ?: "", weight = 0.3f)
    }
}

@Composable
fun CreateHead(modifier: Modifier = Modifier, count: Int) {
    Row(
        modifier
            .background(Color.Gray)
            .height(IntrinsicSize.Min)
    ) {
        TableCell(modifier, text = "", weight = 0.3f)
        TableCell(modifier, text = "", weight = 0.1f)
        for (i in 1..count) {
            TableCell(modifier, text = "$i", weight = 0.1f)
        }
        TableCell(modifier, text = "Сумма очков", weight = 0.3f)
        TableCell(modifier, text = "Место", weight = 0.3f)
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