package com.sonasetiana.techtestkozokku

import android.app.Application
import com.sonasetiana.techtestkozokku.data.di.databaseModule
import com.sonasetiana.techtestkozokku.data.di.networkModule
import com.sonasetiana.techtestkozokku.data.di.repositoryModule
import com.sonasetiana.techtestkozokku.domain.di.domainModules
import com.sonasetiana.techtestkozokku.presentation.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    domainModules,
                    presentationModules
                )
            )
        }
    }
}