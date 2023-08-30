package com.arifandi.capstone.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Capstone")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "newsId")
    var newsId: Long = 0,

    @ColumnInfo(name = "author")
    var author: String?,

    @ColumnInfo(name = "content")
    var content: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "url")
    var url: String?,

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)