package com.dheepak.giphytrending

import android.app.Application
import com.dheepak.giphytrending.di.trendingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(trendingModule)
        }
    }
}