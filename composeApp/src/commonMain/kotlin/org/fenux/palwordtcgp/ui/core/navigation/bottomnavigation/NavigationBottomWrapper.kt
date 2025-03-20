package org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.fenux.palwordtcgp.ui.core.navigation.CardDetail
import org.fenux.palwordtcgp.ui.core.navigation.Routes
import org.fenux.palwordtcgp.ui.home.tabs.cards.CardsScreen
import org.fenux.palwordtcgp.ui.home.tabs.community.CommunityScreen
import org.fenux.palwordtcgp.ui.home.tabs.duels.DuelsScreen
import org.fenux.palwordtcgp.ui.home.tabs.home.HomeTabScreen
import org.fenux.palwordtcgp.ui.home.tabs.menu.MenuScreen


@Composable
fun NavigationBottomWrapper(
    navController: NavHostController,
    mainNavController: NavHostController
) {

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeTabScreen()
        }
        composable(route = Routes.Cards.route) {
            CardsScreen(
                navigationToDetail = { cardModel ->
                    val encode: String = Json.encodeToString(cardModel)
                    mainNavController.navigate(CardDetail(encode))
                }
            )
        }
        composable(route = Routes.Community.route) {
            CommunityScreen()
        }
        composable(route = Routes.Menu.route) {
            MenuScreen()
        }
        composable(route = Routes.Duels.route) {
            DuelsScreen()
        }
    }
}