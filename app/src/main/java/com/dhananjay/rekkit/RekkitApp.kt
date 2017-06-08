package com.dhananjay.rekkit

import android.app.Application
import com.dhananjay.rekkit.di.AppModule
import com.dhananjay.rekkit.di.DaggerNewsComponent
import com.dhananjay.rekkit.di.NewsComponent

/**
 * Created by dhananjay on 8/6/17.
 */

class RekkitApp : Application(){

    companion object{
        lateinit var newsComponent : NewsComponent
    }

    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}