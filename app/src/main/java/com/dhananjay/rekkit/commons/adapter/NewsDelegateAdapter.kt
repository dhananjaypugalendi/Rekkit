package com.dhananjay.rekkit.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dhananjay.rekkit.R
import com.dhananjay.rekkit.commons.RedditNewsItem
import com.dhananjay.rekkit.commons.getFriendlyTime
import com.dhananjay.rekkit.commons.inflate
import com.dhananjay.rekkit.commons.loadImage
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by dhananjay on 5/6/17.
 */

class NewsDelegateAdapter: ViewTypeDelegateAdapter{

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)){

        fun bind(item: RedditNewsItem) = with(itemView){
            img_thumbnail.loadImage(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }

}