package com.example.booktrackercompose.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.booktrackercompose.screens.authorization.login.LogInScreen
import com.example.booktrackercompose.screens.authorization.registration.RegistrationScreen
import com.example.booktrackercompose.screens.library.Library
import com.example.booktrackercompose.screens.search.SeachScreen
import com.example.booktrackercompose.screens.settings.SettingsScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentRoute = currentRoute(navController)
            if (Screen.Library.withBottomBar() && currentRoute in listOf(
                    Screen.Library.route,
                    Screen.Setting.route,
                    Screen.Search.route
                )) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.LogIn.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.LogIn.route) { LogInScreen(navController) }
            composable(Screen.Registration.route) { RegistrationScreen(navController) }
            composable(Screen.Library.route) { Library(navController) }
            composable(Screen.Setting.route) { SettingsScreen(navController) }
            composable(Screen.Search.route) { SeachScreen(navController) }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarItems = listOf(
        Screen.Search,
        Screen.Library,
        Screen.Setting
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBar(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.09f),
            containerColor = colorScheme.background,
            tonalElevation = 8.dp
        ) {
            bottomBarItems.forEach { screen ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(screen.icon),
                            contentDescription = screen.route,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorScheme.onSecondary,
                        unselectedIconColor = colorScheme.primary,
                        indicatorColor = colorScheme.onPrimary
                    )
                )
            }
        }
    }
}