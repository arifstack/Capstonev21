package com.arifandi.capstone.core.di

import com.arifandi.capstone.core.data.NewsRepository
import com.arifandi.capstone.core.domain.repository.InewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: NewsRepository): InewsRepository

}