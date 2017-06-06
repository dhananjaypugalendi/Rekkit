package com.dhananjay.rekkit.commons.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.dhananjay.rekkit.R
import com.dhananjay.rekkit.commons.inflate

/**
 * Created by dhananjay on 5/6/17.
 */

class LoadingDelegateAdapter : ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_loading_item)
    )
}