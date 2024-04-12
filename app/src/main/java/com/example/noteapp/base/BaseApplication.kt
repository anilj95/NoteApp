package com.example.noteapp.base

import android.app.Application
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BaseApplication)
        }
    }
}