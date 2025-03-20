package org.fenux.palwordtcgp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.fenux.palwordtcgp.domain.model.CardModel
import org.fenux.palwordtcgp.ui.detail.ui.MainHeader
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parameterSetOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CardDetailScreen(cardModel: CardModel, onBackPressed: () -> Unit) {

    val cardDetailViewModel =
        koinViewModel<CardDetailViewModel>(parameters = { parameterSetOf(cardModel) })

    val state by cardDetailViewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color.Blue)){
        MainHeader(state.cardModel, onBackPressed)
    }

}

