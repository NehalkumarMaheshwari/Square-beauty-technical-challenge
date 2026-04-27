package com.elf.square.data.repository

import com.elf.square.data.api.GitHubService
import com.elf.square.data.model.Repository

/**
 * Interface defining the operations for fetching repository data.
 */
interface RepoRepository {
    /**
     * Fetches a list of repositories.
     */
    suspend fun getRepositories(): List<Repository>
}

/**
 * Implementation of [RepoRepository] that uses [GitHubService] to fetch data from the network.
 */
class RepoRepositoryImpl(private val api: GitHubService) : RepoRepository {
    override suspend fun getRepositories(): List<Repository> {
        // Calls the GitHub API service
        return api.getSquareRepos()
    }
}
