package com.example.booktrackercompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.booktrackercompose.Constants
import com.example.booktrackercompose.screens.library.Library
import com.example.booktrackercompose.screens.search.SeachScreen
import com.example.booktrackercompose.screens.settings.SettingsScreen

@Composable
fun BookTrackerApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "library",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("search") { SeachScreen() }
            composable("library") { Library() }
            composable("settings") { SettingsScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    val colorScheme = MaterialTheme.colorScheme

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            Modifier.fillMaxWidth().height(1.dp).background(colorScheme.secondary)
        )
        NavigationBar(
            modifier = Modifier.fillMaxHeight(0.11f),
            containerColor = MaterialTheme.colorScheme.background,
        ){
            Constants.BottomNavItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.image),
                            contentDescription = item.title,
                            modifier = Modifier.size(20.dp)
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