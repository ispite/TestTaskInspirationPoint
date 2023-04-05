package com.example.testtaskinspirationpoint

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.testtaskinspirationpoint.data.Participant

class MainViewModel : ViewModel() {

    private val _participants = createStartParticipants().toMutableStateList()
    val participants: List<Participant>
        get() = _participants

    fun changeParticipantCompetitionResult(
        participantId: Int,
        competitionId: Int,
        competitionResult: Int?
    ) {
        Log.d("ViewModel", "changeParticipantCompetitionResult: STARTED")
        participants.forEach { participant ->
            participant.competitions.forEach { compResult ->
                Log.d(
                    "ViewModel",
                    "participant ID: ${participant.id} compResult ID: ${compResult.id} compResult: ${compResult.competitionResult}"
                )
            }
        }
        participants.find { it.id == participantId }?.let { prtcpnt ->
            prtcpnt.competitions.find { it.id == competitionId }
                ?.let { result -> result.competitionResult = competitionResult }
        }
        checkCompResultCount(participantId)
    }

    private fun checkCompResultCount(participantId: Int) {
        participants.find { it.id == participantId }?.let { prtcpnt ->
            val predicate: (Int?) -> Boolean = { it != null }
            val notNullCount = prtcpnt.competitions.map { it.competitionResult }.count(predicate)
            Log.d("ViewModel", "checkCompResultCount notNullCount: $notNullCount ")

            val sum = prtcpnt.competitions.map { it.competitionResult }.filterNotNull().sum()

            if (prtcpnt.competitions.size - 1 == notNullCount) prtcpnt.pointsTotal = sum
            else prtcpnt.pointsTotal = null
        }
    }
}

fun createStartParticipants(count: Int = 7): List<Participant> =
    List(count) { i -> Participant(i, count) }