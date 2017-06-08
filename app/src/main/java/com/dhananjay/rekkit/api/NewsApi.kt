package com.dhananjay.rekkit.api

import retrofit2.Call

/**
 * Created by dhananjay on 8/6/17.
 */
interface NewsApi {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}