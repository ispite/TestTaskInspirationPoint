package com.example.testtaskinspirationpoint.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Participant(val id: Int, private val competitionCount: Int, initialPoints: Int? = null) {

    var pointsTotal by mutableStateOf(initialPoints)

    val competitions =
        MutableList<CompetitionResult>(competitionCount) { i -> CompetitionResult(i) }
}