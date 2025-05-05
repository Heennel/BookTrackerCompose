package com.example.booktrackercompose.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booktrackercompose.screens.BookTrackerApp
import com.example.booktrackercompose.screens.settings.SettingsViewModel
import com.example.booktrackercompose.ui.theme.BookTrackerComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themeViewModel: SettingsViewModel = hiltViewModel()
            val darkThemeState by themeViewModel.isDarkTheme

            BookTrackerComposeTheme(
                darkTheme = darkThemeState
            ){
                BookTrackerApp()
            }
        }
    }
}

