package com.sindrenm.lwb.app.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: RecordsRepository,
) : ViewModel() {
    var viewState: HomeViewState by mutableStateOf(HomeViewState.Loading)
        private set

    init {
        loadRecords()
    }

    private fun loadRecords() {
        viewModelScope.launch {
            repository.getRecords()
                .collect { viewState = HomeViewState.Content(it) }
        }
    }
}

sealed interface HomeViewState {
    object Loading : HomeViewState

    data class Content(val records: List<Record>) : HomeViewState
}
