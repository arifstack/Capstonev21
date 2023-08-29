package com.arifandi.capstone.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NewsDao {

    @Query("SELECT * FROM newsapp")
    fun getAllNews(): Flowable<List<NewsEntity>>

    @Query("SELECT * FROM newsapp WHERE newsId = :newsId")
    fun getNewsDetail(newsId: String): Flowable<NewsEntity>

    @Query("SELECT * FROM newsapp where isFavorite = 1")
    fun getFavoriteNews(): Flowable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(tourism: List<NewsEntity>): Completable

    @Query("UPDATE newsapp SET isFavorite = :newState WHERE newsId = :newsId")
    fun updateFavoriteNews(newsId: String, newState: Boolean)
}