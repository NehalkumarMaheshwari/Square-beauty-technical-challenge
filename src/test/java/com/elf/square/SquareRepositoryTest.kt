package com.elf.square

import com.elf.square.data.api.GitHubService
import com.elf.square.data.model.Repository
import com.elf.square.data.repository.RepoRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit test for the RepoRepository implementation.
 */
class SquareRepositoryTest {

    private val api: GitHubService = mockk()
    private val repository = RepoRepositoryImpl(api)

    @Test
    fun `getRepositories should return list from API`() = runBlocking {
        // Given
        val mockRepos = listOf(Repository(1, "square-repo", "desc"))
        coEvery { api.getSquareRepos() } returns mockRepos

        // When
        val result = repository.getRepositories()

        // Then
        assertEquals(mockRepos, result)
    }
}
