package com.elf.square.data.api

import com.elf.square.data.model.Repository
import retrofit2.http.GET

/**
 * Retrofit interface for GitHub API calls.
 */
interface GitHubService {
    /**
     * Fetches all repositories for the "square" organization.
     * @return A list of [Repository] objects.
     */
    @GET("orgs/square/repos")
    suspend fun getSquareRepos(): List<Repository>
}
