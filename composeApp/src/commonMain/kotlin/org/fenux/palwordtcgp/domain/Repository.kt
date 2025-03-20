package org.fenux.palwordtcgp.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.fenux.palwordtcgp.domain.model.CardModel

interface Repository {
    suspend fun getSingleCard(id:String): CardModel
    fun getAllCards():Flow<PagingData<CardModel>>
    fun getAllCard():Flow<PagingData<CardModel>>
}