package com.elf.square

import android.app.Application
import com.elf.square.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Custom Application class for the app.
 * Used to initialize Koin Dependency Injection.
 */
class SquareApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Start Koin dependency injection
        startKoin {
            // Log Koin events
            androidLogger()
            // Reference Android context
            androidContext(this@SquareApplication)
            // Load modules
            modules(appModule)
        }
    }
}
