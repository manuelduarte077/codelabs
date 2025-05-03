package dev.donmanuel.dailyquotes

import android.app.Application
import dev.donmanuel.dailyquotes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class QuoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuoteApp)
            modules(appModule)
        }
    }
}