package com.example.testmobileca_kmp.modules.account.presentation.banksList.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmobileca_kmp.modules.account.domain.entities.Bank
import com.example.testmobileca_kmp.modules.account.domain.usecases.GetSortedBanksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class BanksListState {
    object Loading : BanksListState()
    data class Success(val banks: List<Bank>) : BanksListState()
    data class Failure(val error: String) : BanksListState()
}

class BanksListViewModel(private val getSortedBanksUseCase: GetSortedBanksUseCase) : ViewModel() {

    private val _viewState = MutableStateFlow<BanksListState>(BanksListState.Loading)
    val viewState: StateFlow<BanksListState> = _viewState.asStateFlow()

    init {
        fetchBanks()
    }

    fun fetchBanks() {
        _viewState.value = BanksListState.Loading
        viewModelScope.launch {
            val result = getSortedBanksUseCase.execute()
            result
                .onSuccess { banks -> _viewState.value = BanksListState.Success(banks) }
                .onFailure { error ->
                    _viewState.value = BanksListState.Failure(error.message ?: "Unknown error")
                }
        }
    }
}
