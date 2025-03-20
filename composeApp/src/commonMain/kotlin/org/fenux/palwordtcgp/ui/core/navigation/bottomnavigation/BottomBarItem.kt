package org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import org.fenux.palwordtcgp.ui.core.navigation.Routes
import org.jetbrains.compose.resources.painterResource
import palwordtcgp.composeapp.generated.resources.Res
import palwordtcgp.composeapp.generated.resources.ic_card

sealed class BottomBarItem {
    abstract val route:String
    abstract val title:String
    abstract val icon: @Composable () -> Unit

    data class Home(
        override val route: String = Routes.Home.route,
        override val title: String = "Home",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, "")
        }
    ):BottomBarItem()

    data class Cards(
        override val route: String = Routes.Cards.route,
        override val title: String = "Cards",
        override val icon: @Composable () -> Unit = {
            Icon(painter = painterResource(Res.drawable.ic_card), "")
        }
    ):BottomBarItem()

    data class Community(
        override val route: String = Routes.Community.route,
        override val title: String = "Community",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.CheckCircle, "")
        }
    ):BottomBarItem()

    data class Duels(
    override val route: String = Routes.Duels.route,
    override val title: String = "Duels",
    override val icon: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.Person, "")
    }
    ):BottomBarItem()

    data class Menu(
        override val route: String = Routes.Menu.route,
        override val title: String = "Menu",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Menu, "")
        }
    ):BottomBarItem()


}