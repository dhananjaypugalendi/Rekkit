package com.dhananjay.rekkit


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dhananjay.rekkit.commons.NewsManager
import com.dhananjay.rekkit.commons.adapter.NewsAdapter
import com.dhananjay.rekkit.commons.inflate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : RxBaseFragment() {

    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_news)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if(savedInstanceState == null){
            requestNews()
        }
    }

    private fun requestNews(){
        compositeDisposable.add(newsManager.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews)
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
