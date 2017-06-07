package com.dhananjay.rekkit


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhananjay.rekkit.commons.InfiniteScrollListener
import com.dhananjay.rekkit.commons.NewsManager
import com.dhananjay.rekkit.commons.RedditNews
import com.dhananjay.rekkit.commons.adapter.NewsAdapter
import com.dhananjay.rekkit.commons.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment  : RxBaseFragment(){

    companion object{
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null
    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        initAdapter()

        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)){
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.isNotEmpty()){
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews(){
        compositeDisposable.add(newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?:"", Snackbar.LENGTH_LONG).show()   
                        }
                ))
    }

    private fun initAdapter(){
        if(news_list.adapter == null){
            news_list.adapter = NewsAdapter()
        }
    }

}
