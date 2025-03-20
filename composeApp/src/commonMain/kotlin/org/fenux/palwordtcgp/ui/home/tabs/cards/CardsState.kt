package org.fenux.palwordtcgp.ui.home.tabs.cards

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.fenux.palwordtcgp.domain.model.CardModel

data class CardsState(
    val cardOfTheDay: CardModel? = null,
    val cards: Flow<PagingData<CardModel>> = emptyFlow()
)
