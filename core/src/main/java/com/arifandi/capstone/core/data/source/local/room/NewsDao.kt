package com.arifandi.capstone.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import com.arifandi.capstone.core.domain.model.News
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM capstone")
    fun getAllNews(): Flowable<List<NewsEntity>>

    @Query("SELECT * FROM capstone WHERE newsId = :newsId")
    fun getNewsDetail(newsId: String): Flowable<NewsEntity>

    @Query("SELECT * FROM capstone where isFavorite = 1")
    fun getFavoriteNews(): Flowable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(tourism: List<NewsEntity>): Completable

    @Query("UPDATE capstone SET isFavorite = :newState WHERE newsId = :newsId")
    fun updateFavoriteNews(newsId: String, newState: Boolean)
}