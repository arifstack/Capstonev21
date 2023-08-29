package com.arifandi.capstone.core.data.source.local

import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import com.arifandi.capstone.core.data.source.local.room.NewsDao
import com.arifandi.capstone.core.domain.model.News
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(newsDao: NewsDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(newsDao)
            }
    }

    fun getAllNews(): Flowable<List<NewsEntity>> = newsDao.getAllNews()

    fun getNewsDetail(newsId: String): Flowable<NewsEntity> = newsDao.getNewsDetail(newsId)

    fun getFavoriteNews(): Flowable<List<NewsEntity>> = newsDao.getFavoriteNews()

    fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    fun setFavoriteNews(news: NewsEntity, newState: Boolean) {
        newsDao.updateFavoriteNews(news.newsId.toString(), newState)
    }
}