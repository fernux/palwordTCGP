package org.fenux.palwordtcgp.ui.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.fenux.palwordtcgp.domain.model.CardModel

class CardDetailViewModel(cardModel: CardModel):ViewModel() {

    private  val _uiState = MutableStateFlow<CardDetailState>(CardDetailState(cardModel))
    val uiState: StateFlow<CardDetailState> = _uiState

}