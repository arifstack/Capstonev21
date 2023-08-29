package com.arifandi.capstone.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.arifandi.capstone.core.domain.model.News

class NewsDiffCallback (private val mOldCharacterList : List<News>, private val mNewCharacterList: List<News>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = mOldCharacterList.size

    override fun getNewListSize(): Int = mNewCharacterList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldCharacterList[oldItemPosition].newsId == mNewCharacterList[newItemPosition].newsId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldCharacterList[oldItemPosition]
        val newItem = mNewCharacterList[newItemPosition]
        return (oldItem.newsId == newItem.newsId &&
                oldItem.isFavorite == newItem.isFavorite &&
                oldItem.title == newItem.title &&
                oldItem.content == newItem.content &&
                oldItem.description == newItem.description &&
                oldItem.publishedAt == newItem.publishedAt &&
                oldItem.url == newItem.url &&
                oldItem.urlToImage == newItem.urlToImage &&
                oldItem.author == newItem.author)
    }
}