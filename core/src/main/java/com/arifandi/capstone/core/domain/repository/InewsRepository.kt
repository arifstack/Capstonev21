package com.arifandi.capstone.core.domain.repository

import com.arifandi.capstone.core.data.Resource
import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import com.arifandi.capstone.core.domain.model.News
import io.reactivex.Flowable

interface InewsRepository {
    fun getAllNews(): Flowable<Resource<List<News>>>

    fun getNewsDetail(newsId: String): Flowable<News>
    fun getFavoriteNews(): Flowable<List<News>>
    fun setFavoriteNews(tourism: News, state: Boolean)

}