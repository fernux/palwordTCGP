package org.fenux.palwordtcgp.ui.core.navigation

import kotlinx.serialization.Serializable

sealed class Routes (val route: String){
   data object Home:Routes("home")




   //BottomNav
   data object Cards:Routes("cards")
   data object Community:Routes("community")
   data object Duels:Routes("duels")
   data object Menu:Routes("menu")
}

@Serializable
data class CardDetail(val cardModel: String)