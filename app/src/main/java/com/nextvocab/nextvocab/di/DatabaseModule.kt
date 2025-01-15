package com.nextvocab.nextvocab.di

import android.content.Context
import androidx.room.Room
import com.nextvocab.nextvocab.data.local.database.AppDatabase
import com.nextvocab.nextvocab.data.local.database.WordDao
import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import com.nextvocab.nextvocab.data.remote.ApiService
import com.nextvocab.nextvocab.data.repository.WordsRepositoryImpl
import com.nextvocab.nextvocab.domain.repository.WordsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "word_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideWordDao(db: AppDatabase): WordDao {
        return db.wordDao()
    }
    @Provides
    @Singleton
    fun provideWordsRepository(
        apiService: ApiService,
        wordMapper: WordDefinitionMapper,
        wordDao: WordDao
    ): WordsRepository {
        return WordsRepositoryImpl(apiService, wordMapper, wordDao)
    }
}