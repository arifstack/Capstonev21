package com.arifandi.capstone.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("author")
    val author: String?,
    @field:SerializedName("content")
    val content: String?,
    @field:SerializedName("description")
    val description: String?,
    @field:SerializedName("publishedAt")
    val publishedAt: String?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("url")
    val url: String?,
    @field:SerializedName("urlToImage")
    val urlToImage: String?
)