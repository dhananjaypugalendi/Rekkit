package com.dhananjay.rekkit.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Created by dhananjay on 7/6/17.
 */

class NewsRestApi @Inject constructor(private val redditApi: RedditApi) :NewsApi{

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse>{
        return redditApi.getTop(after, limit)
    }
}