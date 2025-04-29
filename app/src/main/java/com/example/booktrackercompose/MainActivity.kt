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
import com.example.booktrackercompose.screens.search.SearchResults
import com.example.booktrackercompose.screens.search.SearchStatus
import com.example.booktrackercompose.screens.search.TopTextField
import com.example.booktrackercompose.screens.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val query by viewModel.searchQuery.collectAsState()
            val bookList by viewModel.bookList.collectAsState()
            val searchStatus by viewModel.searchStatus.collectAsState()

            Column(
                modifier = Modifier.systemBarsPadding()
            ) {
                TopTextField(
                    value = query,
                    onChangeValue = viewModel::onChangeQuery
                )
                SearchResults(bookList, searchStatus)
            }
        }
    }
}
