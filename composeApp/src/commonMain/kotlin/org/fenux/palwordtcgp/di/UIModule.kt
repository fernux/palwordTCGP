package org.fenux.palwordtcgp.di

import org.fenux.palwordtcgp.ui.detail.CardDetailViewModel
import org.fenux.palwordtcgp.ui.home.tabs.cards.CardsViewModel
import org.fenux.palwordtcgp.ui.home.tabs.home.HomeTabViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {

    viewModelOf(::HomeTabViewModel)
    viewModelOf(::CardsViewModel)
    viewModelOf(::CardDetailViewModel)
}