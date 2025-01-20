package com.nextvocab.nextvocab.di

import android.content.Context
import androidx.room.Room
import com.nextvocab.nextvocab.data.local.database.AppDatabase
import com.nextvocab.nextvocab.data.local.database.FlashCardDao
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionMapper
import com.nextvocab.nextvocab.data.remote.WordDefinitionService
import com.nextvocab.nextvocab.data.repository.flashcard.FlashCardsRepositoryImpl
import com.nextvocab.nextvocab.data.repository.flashcard.FlashCardsRepository
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionRepository
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionRepositoryImpl
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
    fun provideWordDao(db: AppDatabase): FlashCardDao {
        return db.wordDao()
    }

    @Provides
    @Singleton
    fun provideFlashCardsRepository(
        flashCardDao: FlashCardDao
    ): FlashCardsRepository {
        return FlashCardsRepositoryImpl(flashCardDao)
    }

}