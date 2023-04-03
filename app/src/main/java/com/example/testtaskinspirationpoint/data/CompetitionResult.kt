package com.example.testtaskinspirationpoint.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CompetitionResult(val id: Int, initialResult: Int? = 0) {

    var competitionResult by mutableStateOf(initialResult)
}