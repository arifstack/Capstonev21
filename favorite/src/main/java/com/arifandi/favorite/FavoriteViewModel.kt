package com.arifandi.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.arifandi.capstone.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(newsUseCase: NewsUseCase) : ViewModel() {
    val favoriteTourism = LiveDataReactiveStreams.fromPublisher(newsUseCase.getFavoriteNews())

}
