package com.arifandi.capstone.core.domain.usecase

import com.arifandi.capstone.core.data.Resource
import com.arifandi.capstone.core.domain.model.News
import io.reactivex.Flowable

interface NewsUseCase {
    fun getAllNews(): Flowable<Resource<List<News>>>
    fun getNewsDetail(newsId: String): Flowable<News>
    fun getFavoriteNews(): Flowable<List<News>>
    fun setFavoriteNews(news: News, state: Boolean)
}