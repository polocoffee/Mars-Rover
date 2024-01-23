package com.banklannister.marsrover.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.banklannister.marsrover.nav.Destination.Home
import com.banklannister.marsrover.nav.Destination.Manifest
import com.banklannister.marsrover.nav.Destination.Photo
import com.banklannister.marsrover.nav.Destination.Saved
import com.banklannister.marsrover.ui.theme.MarsRoverTheme
import com.banklannister.marsrover.ui.view.ManifestScreen
import com.banklannister.marsrover.ui.view.PhotoListSavedScreen
import com.banklannister.marsrover.ui.view.PhotoScreen
import com.banklannister.marsrover.ui.view.RoverList


@Composable
fun NavCompose() {
    val navController = rememberNavController()
    val action = remember(navController) { Action(navController) }
    val items = listOf(Screen.Home, Screen.Saved)


    MarsRoverTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.drawableId),
                                    contentDescription = stringResource(id = screen.resourceId)
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = screen.resourceId)
                                )
                            },
                            selected = currentDestination?.hierarchy?.any {
                                if (it.route?.contains("manifest") == true || it.route?.contains("photo") == true) {
                                    screen.route == "home"
                                } else {
                                    it.route == screen.route
                                }
                            } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)

            NavHost(navController = navController, startDestination = Home) {
                composable(Home) {
                    RoverList(
                        modifier = Modifier
                    ) { roverName ->
                        action.manifest(roverName)
                    }
                }

                composable(Manifest) { backStackEntry ->
                    ManifestScreen(
                        modifier = Modifier,
                        roverName = backStackEntry.arguments?.getString("roverName"),
                        manifestViewModel = hiltViewModel(),
                        onClick = { roverName, sol ->
                            action.photo(roverName, sol)
                        }
                    )
                }

                composable(Photo) { backStackEntry ->
                    PhotoScreen(
                        modifier = Modifier,
                        roverName = backStackEntry.arguments?.getString("roverName"),
                        sol = backStackEntry.arguments?.getString("sol"),
                        photoViewModel = hiltViewModel()
                    )
                }

                composable(Saved) {
                    PhotoListSavedScreen(
                        modifier = modifier,
                        savedViewModel = hiltViewModel()
                    )
                }
            }
        }
    }
}