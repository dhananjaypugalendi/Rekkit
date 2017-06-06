package com.dhananjay.rekkit.commons

import com.dhananjay.rekkit.commons.adapter.AdapterConstants
import com.dhananjay.rekkit.commons.adapter.ViewType

/**
 * Created by dhananjay on 5/6/17.
 */
data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType{
    override fun getViewType() = AdapterConstants.NEWS
}