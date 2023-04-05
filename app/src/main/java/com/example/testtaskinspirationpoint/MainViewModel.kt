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
/*                Log.d(
                    "ViewModel",
                    "participant ID: ${participant.id} compResult ID: ${compResult.id} compResult: ${compResult.competitionResult}"
                )*/
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

            val sum = prtcpnt.competitions.mapNotNull { it.competitionResult }.sum()

            if (prtcpnt.competitions.size - 1 == notNullCount) prtcpnt.pointsTotal = sum
            else prtcpnt.pointsTotal = null
        }
//        participants.forEach { it.pointsTotal.cou }
//        val total = participants.map { it.pointsTotal }.count()
        val total = participants.mapNotNull { it.pointsTotal }.count()
        Log.d("ViewModel", "participants.size ${participants.size} total $total")
        val timeForReward = participants.size == participants.mapNotNull { it.pointsTotal }.count()
        if (timeForReward) {
            //TODO надо сделать проверку на одинаковое количество очков
            // и соответственно одинаковое место для участников
//            val count = participants.forEach { it.pointsTotal in participants.map { it.pointsTotal } }
//            val count = participants.forEach { /*it.pointsTotal in*/ participants.map { it.pointsTotal }.contains(it.pointsTotal) }
            val count = participants.forEach { participant -> /*it.pointsTotal in*/ participants.map { it.pointsTotal }.find {it == participant.pointsTotal} }

            Log.d("ViewModel", "timeForReward")
            val participantFromWorstToFirst = participants.sortedBy { it.pointsTotal }
            participantFromWorstToFirst.forEachIndexed { i, rangedParticipant ->
//                Log.d("ViewModel", "participant.id ${it.id} participant.pointsTotal ${it.pointsTotal}")
                participants.find { it.id == rangedParticipant.id }
                    .let { it?.place = participantFromWorstToFirst.size - i }
            }
        }
    }
}

fun createStartParticipants(count: Int = 7): List<Participant> =
    List(count) { i -> Participant(i, count) }