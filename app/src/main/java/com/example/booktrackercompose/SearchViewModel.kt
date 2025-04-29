package com.example.booktrackercompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val bookApi: GoogleBooksApi
): ViewModel() {

    private var searchJob: Job? = null

    private var _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onChangeQuery(query: String){
        _searchQuery.value = query
    }

    init {
        searchEngine()
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchEngine(){
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .filter { it.isNotBlank() }
                .flatMapLatest { query ->
                    searchBook(query)
                }
                .flowOn(Dispatchers.IO)
                .collect{ result ->

                }
        }

    }

    private fun searchBook(query: String): Flow<BookResponse> = flow {
        val result = bookApi.getBooks(query, API_KEY)
        emit(result)
    }

    companion object{
        private const val API_KEY = "AIzaSyDLEeZxBnsGuDGjXt7ta8NgnmMc-Nt665I"
    }
}