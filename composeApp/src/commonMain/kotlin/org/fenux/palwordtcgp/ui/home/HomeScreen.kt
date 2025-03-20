package org.fenux.palwordtcgp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.BottomBarItem
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.BottomBarItem.Home
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.BottomBarItem.Cards
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.BottomBarItem.Community
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.BottomBarItem.Duels
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.BottomBarItem.Menu
import org.fenux.palwordtcgp.ui.core.navigation.bottomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(Home(),Cards(), Community(), Duels(), Menu())
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(items,navController) }) { padding ->
        Box (modifier = Modifier.padding(padding)){
            NavigationBottomWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                onClick = {
                    navController.navigate(route =  item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            )
        }
    }
}