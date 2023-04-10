package com.example.testtaskinspirationpoint

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.testtaskinspirationpoint.data.Config
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

            val sum = prtcpnt.competitions.mapNotNull { it.competitionResult }.sum()

            if (prtcpnt.competitions.size - 1 == notNullCount) prtcpnt.pointsTotal = sum
            else prtcpnt.pointsTotal = null
        }
        val timeForReward = participants.size == participants.mapNotNull { it.pointsTotal }.count()
        if (timeForReward) {
            val participantFromFirstToLast = participants.sortedByDescending { it.pointsTotal }
            var placeCounter = 0
            participantFromFirstToLast.forEachIndexed { i, rangedParticipant ->
                if (participantFromFirstToLast.indices.contains(i - 1)) {
                    if (participantFromFirstToLast[i - 1].pointsTotal == rangedParticipant.pointsTotal) {
                        participants.find { it.id == rangedParticipant.id }
                            .let { it?.place = placeCounter }
                    } else {
                        participants.find { it.id == rangedParticipant.id }
                            .let {
                                placeCounter += 1
                                it?.place = placeCounter
                            }
                    }
                } else {
                    participants.find { it.id == rangedParticipant.id }
                        .let {
                            placeCounter += 1
                            it?.place = placeCounter
                        }
                }
            }
        }
    }
}

fun createStartParticipants(count: Int = Config.COUNT): List<Participant> =
    List(count) { i -> Participant(i, count) }