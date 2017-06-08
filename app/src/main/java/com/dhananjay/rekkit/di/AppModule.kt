package com.dhananjay.rekkit.di

import android.content.Context
import com.dhananjay.rekkit.RekkitApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dhananjay on 8/6/17.
 */

@Module
class AppModule(val app: RekkitApp){

    @Provides
    @Singleton
    fun provideContext() = app

    @Provides
    @Singleton
    fun provideApplication() = app
}
