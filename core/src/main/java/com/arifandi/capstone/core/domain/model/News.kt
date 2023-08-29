package com.arifandi.capstone.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class News(
    var newsId:Long,
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: String,
    var title: String,
    var url: String,
    var urlToImage: String?,
    val isFavorite: Boolean
):Parcelable