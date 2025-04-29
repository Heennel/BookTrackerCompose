package com.example.booktrackercompose.screens.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booktrackercompose.api.Book
import com.example.booktrackercompose.api.BookResponse
import com.example.booktrackercompose.api.GoogleBooksApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val bookApi: GoogleBooksApi
): ViewModel() {

    private var searchJob: Job? = null

    private var _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var _bookList = MutableStateFlow<List<Book>>(emptyList())
    val bookList: StateFlow<List<Book>> = _bookList.asStateFlow()

    private var _searchStatus = MutableStateFlow(SearchStatus.BASE)
    val searchStatus: StateFlow<SearchStatus> = _searchStatus

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
                .onEach {
                    if (it.isBlank()){
                        clearList()
                        changeSearchStatus(SearchStatus.BASE)
                    }
                }
                .debounce(300)
                .distinctUntilChanged()
                .filter { it.isNotBlank()}
                .flatMapLatest { query ->
                    searchBook(query)
                }
                .flowOn(Dispatchers.IO)
                .collect{ result ->
                    if(result.totalItems==0) {
                        clearList()
                        changeSearchStatus(SearchStatus.NOT_FOUND)
                    }
                    else
                        updateUI(result)
                }
        }

    }

    private fun searchBook(query: String): Flow<BookResponse> = flow {
        changeSearchStatus(SearchStatus.LOADING)
        try {
            val result = bookApi.getBooks(query, API_KEY)
            emit(result)
        }catch (e: Exception){
            changeSearchStatus(SearchStatus.ERROR)
        }
    }

    private fun updateUI(result: BookResponse){
        val resultList = result.items?.map { it.bookItem } ?: emptyList()
        _bookList.value = resultList
        changeSearchStatus(SearchStatus.SUCCESS)
    }

    private fun clearList(){
        _bookList.value = emptyList()
    }

    private fun changeSearchStatus(newSearchStatus: SearchStatus){
        _searchStatus.value = newSearchStatus
    }

    fun onRestoreButtonClick(){
        searchEngine()
    }

    companion object{
        private const val API_KEY = "AIzaSyDLEeZxBnsGuDGjXt7ta8NgnmMc-Nt665I"
    }
}