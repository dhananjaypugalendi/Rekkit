package com.dhananjay.rekkit

import com.dhananjay.rekkit.api.*
import com.dhananjay.rekkit.commons.NewsManager
import com.dhananjay.rekkit.commons.RedditNews
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response
import java.util.*

/**
 * Created by dhananjay on 8/6/17.
 */
class NewsManagerTest {

    var testSub = TestObserver<RedditNews>()
    var apiMock = mock<NewsApi>()
    var callMock = mock<Call<RedditNewsResponse>>()

    @Before
    fun setup(){
        testSub = TestObserver<RedditNews>()
        apiMock = mock<NewsApi>()
        callMock = mock<Call<RedditNewsResponse>>()
        `when` (apiMock.getNews(anyString(), anyString())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic(){
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(), null, null))
        val response = Response.success(redditNewsResponse)

        `when` (callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()
    }

    @Test
    fun testSuccess_checkOneNews(){
        val newsData = RedditNewsDataResponse(
                "author",
                "title",
                10,
                Date().time,
                "thumbnail",
                "url"
        )
        val newsResponse = RedditChildrenResponse(newsData)
        val redditNewsResponse = RedditNewsResponse(RedditDataResponse(listOf(newsResponse), null, null))
        val response = Response.success(redditNewsResponse)

        `when`(callMock.execute()).thenReturn(response)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertComplete()

        assert((testSub.events[0][0] as RedditNews).news[0].author == newsData.author)
        assert((testSub.events[0][0] as RedditNews).news[0].title == newsData.title)
    }

    @Test
    fun testError(){
        val responseError = Response.error<RedditNewsResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))

        `when`(callMock.execute()).thenReturn(responseError)

        val newsManager = NewsManager(apiMock)
        newsManager.getNews("").subscribe(testSub)

        assert(testSub.events[2].size == 1 )

    }


}


inline fun <reified T: Any> mock(): T = Mockito.mock(T::class.java)