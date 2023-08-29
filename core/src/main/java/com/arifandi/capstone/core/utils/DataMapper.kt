package com.arifandi.capstone.core.utils

import android.util.Log
import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import com.arifandi.capstone.core.data.source.remote.response.NewsResponse
import com.arifandi.capstone.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<NewsResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val tourism = NewsEntity(
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                isFavorite = false
            )
            newsList.add(tourism)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                newsId = it.newsId,
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(it: News) = NewsEntity(
        newsId = it.newsId,
        author = it.author,
        content = it.content,
        description = it.description,
        publishedAt = it.publishedAt,
        title = it.title,
        url = it.url,
        urlToImage = it.urlToImage,
        isFavorite = it.isFavorite
    )

    fun mapEntityToDomain(it: NewsEntity) = News(
        newsId = it.newsId,
        author = it.author,
        content = it.content,
        description = it.description,
        publishedAt = it.publishedAt,
        title = it.title,
        url = it.url,
        urlToImage = it.urlToImage,
        isFavorite = it.isFavorite
    )
}