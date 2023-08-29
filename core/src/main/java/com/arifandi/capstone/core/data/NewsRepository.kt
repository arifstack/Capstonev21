package com.arifandi.capstone.core.data

import com.arifandi.capstone.core.data.source.local.LocalDataSource
import com.arifandi.capstone.core.data.source.local.entity.NewsEntity
import com.arifandi.capstone.core.data.source.remote.RemoteDataSource
import com.arifandi.capstone.core.data.source.remote.network.ApiResponse
import com.arifandi.capstone.core.data.source.remote.response.NewsResponse
import com.arifandi.capstone.core.domain.model.News
import com.arifandi.capstone.core.domain.repository.InewsRepository
import com.arifandi.capstone.core.utils.AppExecutors
import com.arifandi.capstone.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : InewsRepository {

    companion object {
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllNews(): Flowable<Resource<List<News>>> =object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flowable<List<News>> {
                return localDataSource.getAllNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

        override fun shouldFetch(data: List<News>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): Flowable<ApiResponse<List<NewsResponse>>> =remoteDataSource.getAllNews()

            override fun saveCallResult(data: List<NewsResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.run {
                    insertNews(tourismList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
                }
            }
        }.asFlowable()

    override fun getNewsDetail(newsId: String): Flowable<News> =
        localDataSource.getNewsDetail(newsId).map {
            DataMapper.mapEntityToDomain(it)
        }

    override fun getFavoriteNews(): Flowable<List<News>> {
        return localDataSource.getFavoriteNews().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteNews(tourism: News, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteNews(tourismEntity, state) }
    }
}