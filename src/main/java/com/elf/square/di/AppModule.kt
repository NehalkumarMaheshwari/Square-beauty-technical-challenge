package com.elf.square.di

import com.elf.square.data.api.GitHubService
import com.elf.square.data.repository.RepoRepository
import com.elf.square.data.repository.RepoRepositoryImpl
import com.elf.square.ui.RepoViewModel
import com.elf.square.util.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Koin module for dependency injection.
 * Defines how to provide Network, API, Repository, and ViewModel instances.
 */
val appModule = module {
    
    // Provide OkHttpClient with logging interceptor and custom timeouts
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS) // Handle slow network connection
            .readTimeout(30, TimeUnit.SECONDS)    // Handle slow data download
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    // Provide Retrofit instance for GitHub API
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubService::class.java)
    }

    // Provide NetworkHelper for connectivity checks
    single { NetworkHelper(androidContext()) }
    
    // Provide RepoRepository implementation
    single<RepoRepository> { RepoRepositoryImpl(get()) }

    // Provide RepoViewModel for the UI
    viewModel { RepoViewModel(get(), get()) }
}
