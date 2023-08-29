package com.arifandi.capstone.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.arifandi.capstone.core.domain.model.News
import com.arifandi.capstone.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailNewsViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setFavoriteTourism(news: News, newStatus:Boolean) = newsUseCase.setFavoriteNews(news, newStatus)
    fun getNewsDetail(newsId: String) = LiveDataReactiveStreams.fromPublisher(newsUseCase.getNewsDetail(newsId))
}

