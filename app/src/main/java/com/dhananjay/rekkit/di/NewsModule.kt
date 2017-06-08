package com.dhananjay.rekkit.di

import com.dhananjay.rekkit.api.NewsApi
import com.dhananjay.rekkit.api.NewsRestApi
import com.dhananjay.rekkit.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by dhananjay on 8/6/17.
 */

@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsApi(redditApi: RedditApi) : NewsApi = NewsRestApi(redditApi)

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit) = retrofit.create(RedditApi::class.java)
}