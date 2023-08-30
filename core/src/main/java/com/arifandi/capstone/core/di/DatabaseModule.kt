package com.arifandi.capstone.core.di

import android.content.Context
import androidx.room.Room
import com.arifandi.capstone.core.data.source.local.room.NewsDao
import com.arifandi.capstone.core.data.source.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    val passphrase: ByteArray = SQLiteDatabase.getBytes("Capstone".toCharArray())
    val factory = SupportFactory(passphrase)
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java, "Capstone.db"
    ) .openHelperFactory(factory).build()

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}