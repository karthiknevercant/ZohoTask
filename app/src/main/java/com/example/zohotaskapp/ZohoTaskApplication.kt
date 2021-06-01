package com.example.zohotaskapp

import android.app.Application
import com.example.zohotaskapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ZohoTaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ZohoTaskApplication)
            modules(
                networkModule,
                apiModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}