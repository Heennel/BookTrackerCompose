package com.example.booktrackercompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booktrackercompose.screens.settings.SettingsViewModel

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = Color(0xFFAEAFB4),
    tertiary = Color(0xFFdddddd),
    background = Color(0xFF1A1B22),
    onPrimary = Color(0xFF4E5754),
    onSecondary = Color(0xFFE5E4E2),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1A1B22),
    secondary = Color(0xFFAEAFB4),
    tertiary = Color(0xFFdddddd),
    background = Color.White,
    onPrimary = Color(0xFFbcf3ff),
    onSecondary = Color(0xFF53cbe5),

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


@Composable
fun BookTrackerComposeTheme(
    darkTheme: Boolean = isDarkMode(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
    ){
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}

@Composable
private fun isDarkMode() = when(AppCompatDelegate.getDefaultNightMode()){
    AppCompatDelegate.MODE_NIGHT_NO -> false
    else -> true
}