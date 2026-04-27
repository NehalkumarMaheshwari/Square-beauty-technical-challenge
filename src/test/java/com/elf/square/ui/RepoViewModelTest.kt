package com.elf.square.ui

import app.cash.turbine.test
import com.elf.square.data.model.Repository
import com.elf.square.data.repository.RepoRepository
import com.elf.square.util.NetworkHelper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoViewModelTest {

    private val repository: RepoRepository = mockk()
    private val networkHelper: NetworkHelper = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchRepositories success updates state to Success`() = runTest {
        // Given
        val mockRepos = listOf(Repository(1, "repo1", "desc1"))
        every { networkHelper.isNetworkConnected() } returns true
        coEvery { repository.getRepositories() } returns mockRepos

        // When
        val viewModel = RepoViewModel(repository, networkHelper)

        // Then
        viewModel.uiStateObserver.test {
            assertEquals(RepoUiState.Loading, awaitItem())
            assertEquals(RepoUiState.Success(mockRepos), awaitItem())
        }
    }

    @Test
    fun `fetchRepositories no network updates state to NoNetwork`() = runTest {
        // Given
        every { networkHelper.isNetworkConnected() } returns false

        // When
        val viewModel = RepoViewModel(repository, networkHelper)

        // Then
        viewModel.uiStateObserver.test {
            assertEquals(RepoUiState.Loading, awaitItem())
            assertEquals(RepoUiState.NoNetwork, awaitItem())
        }
    }

    @Test
    fun `fetchRepositories error updates state to Error`() = runTest {
        // Given
        val errorMessage = "API Error"
        every { networkHelper.isNetworkConnected() } returns true
        coEvery { repository.getRepositories() } throws Exception(errorMessage)

        // When
        val viewModel = RepoViewModel(repository, networkHelper)

        // Then
        viewModel.uiStateObserver.test {
            assertEquals(RepoUiState.Loading, awaitItem())
            assertEquals(RepoUiState.Error(errorMessage), awaitItem())
        }
    }
}
