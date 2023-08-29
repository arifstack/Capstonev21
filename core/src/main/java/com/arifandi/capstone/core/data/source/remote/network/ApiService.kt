package com.arifandi.capstone.core.data.source.remote.network

import com.arifandi.capstone.core.data.source.remote.response.ListNewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=us&apiKey=92b10913c4bb4e68bb025491609fea6a")
    fun getList(): Flowable<ListNewsResponse>

}