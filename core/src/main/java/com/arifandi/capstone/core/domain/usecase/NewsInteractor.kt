package com.arifandi.capstone.core.domain.usecase

import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import com.arifandi.capstone.core.domain.model.News
import com.arifandi.capstone.core.domain.repository.InewsRepository
import io.reactivex.Flowable
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val tourismRepository: InewsRepository): NewsUseCase {

    override fun getAllNews() = tourismRepository.getAllNews()

    override fun getNewsDetail(newsId: String): Flowable<News> = tourismRepository.getNewsDetail(newsId)

    override fun getFavoriteNews() = tourismRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, state: Boolean) = tourismRepository.setFavoriteNews(news, state)
}