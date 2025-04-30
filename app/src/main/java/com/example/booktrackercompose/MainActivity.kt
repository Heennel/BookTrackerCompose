package com.example.booktrackercompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.booktrackercompose.screens.Authoriation
import com.example.booktrackercompose.screens.search.SeachScreen
import com.example.booktrackercompose.screens.search.SearchResults
import com.example.booktrackercompose.screens.search.TopTextField
import com.example.booktrackercompose.screens.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Authoriation()
        }
    }
}
