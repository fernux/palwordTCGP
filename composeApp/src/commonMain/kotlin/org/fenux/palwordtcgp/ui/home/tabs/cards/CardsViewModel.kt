package org.fenux.palwordtcgp.ui.home.tabs.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.fenux.palwordtcgp.domain.GetRandomCard
import org.fenux.palwordtcgp.domain.Repository
import org.fenux.palwordtcgp.domain.model.CardModel

class CardsViewModel(private val getRandomCard: GetRandomCard, private val repository: Repository) : ViewModel() {

  private var _state = MutableStateFlow<CardsState>(CardsState())
    private var _state2 = MutableStateFlow<CardsState>(CardsState())
    val state2: StateFlow<CardsState> = _state2

    val state: StateFlow<CardsState> = _state

       init {
           viewModelScope.launch {
               val result: CardModel = withContext(Dispatchers.IO) {
                   getRandomCard()
               }
               _state.update { state -> state.copy(cardOfTheDay = result) }
           }
           getAllCards()
           //getAllCardsP()
       }

    private fun getAllCards() {
        _state.update { state ->
            state.copy(
                cards = repository.getAllCards().cachedIn(viewModelScope)
            )
        }
    }
}