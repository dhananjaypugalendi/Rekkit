package com.dhananjay.rekkit.commons

import com.dhananjay.rekkit.api.NewsApi
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by dhananjay on 7/6/17.
 */

@Singleton
class NewsManager @Inject constructor(private val apiNews: NewsApi) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews>{
        return Observable.create {
            subscriber ->
            val callResponse = apiNews.getNews(after, limit)
            val response = callResponse.execute()

            if(response.isSuccessful){
                val dataResponse = response.body().data
                val news = dataResponse.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments, item.created,
                            item.thumbnail, item.url)
                }
                val redditNews = RedditNews(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news
                )
                subscriber.onNext(redditNews)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}