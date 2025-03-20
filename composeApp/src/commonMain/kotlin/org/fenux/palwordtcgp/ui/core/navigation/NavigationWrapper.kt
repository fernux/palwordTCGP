package org.fenux.palwordtcgp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.json.Json
import org.fenux.palwordtcgp.domain.model.CardModel
import org.fenux.palwordtcgp.ui.detail.CardDetailScreen
import org.fenux.palwordtcgp.ui.home.HomeScreen

@Composable
fun NavigationWrapper(){
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.Home.route){
        composable(route= Routes.Home.route){
            HomeScreen(mainNavController)
        }

        composable<CardDetail> {
            val cardDetailEncoding = it.toRoute<CardDetail>()
            val cardModel = Json.decodeFromString<CardModel>(cardDetailEncoding.cardModel)
            CardDetailScreen(cardModel)
        }
    }
}