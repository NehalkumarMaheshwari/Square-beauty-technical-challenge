package com.elf.square.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elf.square.data.model.Repository
import com.elf.square.data.repository.RepoRepository
import com.elf.square.util.NetworkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Sealed class representing the different states of the Repository screen UI.
 */
sealed class RepoUiState {
    object Loading : RepoUiState()
    data class Success(val repos: List<Repository>) : RepoUiState()
    data class Error(val message: String) : RepoUiState()
    object NoNetwork : RepoUiState()
}

/**
 * ViewModel for managing the state and business logic of the Repository screen.
 * Handles fetching data from the repository and checking network status.
 */
class RepoViewModel(
    private val repository: RepoRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    // Internal mutable state flow for UI state updates
    private val uiStateUpdate = MutableStateFlow<RepoUiState>(RepoUiState.Loading)
    // Public read-only state flow for the UI to observe
    val uiStateObserver: StateFlow<RepoUiState> = uiStateUpdate.asStateFlow()

    init {
        // Initial fetch when ViewModel is created
        fetchRepositories()
    }

    /**
     * Fetches the list of repositories from the GitHub API.
     * Checks for network connectivity before making the call.
     */
    fun fetchRepositories() {
        viewModelScope.launch {
            uiStateUpdate.value = RepoUiState.Loading
            
            // 1. Check network connection
            if (!networkHelper.isNetworkConnected()) {
                uiStateUpdate.value = RepoUiState.NoNetwork
                return@launch
            }

            try {
                // 2. Call the repository to get data
                val repos = repository.getRepositories()
                uiStateUpdate.value = RepoUiState.Success(repos)
            } catch (e: Exception) {
                // 3. Handle any exceptions (API errors, parsing errors, etc.)
                uiStateUpdate.value = RepoUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }
}
