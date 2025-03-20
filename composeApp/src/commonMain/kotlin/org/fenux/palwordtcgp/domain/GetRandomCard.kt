package org.fenux.palwordtcgp.domain

import org.fenux.palwordtcgp.domain.model.CardModel

class GetRandomCard(val repository: Repository) {
    suspend operator fun invoke():CardModel{
        val ramdom:Int = (1..146).random()
        return repository.getSingleCard("xy1-$ramdom")
    }
}