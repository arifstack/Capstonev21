package com.arifandi.capstone.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListNewsResponse(

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("articles")
    val articles: List<NewsResponse>
)
