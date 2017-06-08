package com.dhananjay.rekkit.di

import com.dhananjay.rekkit.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dhananjay on 8/6/17.
 */

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent {
    fun inject(newsFragment: NewsFragment)
}