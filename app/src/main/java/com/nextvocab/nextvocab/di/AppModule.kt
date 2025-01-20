package com.nextvocab.nextvocab.di

import com.nextvocab.nextvocab.data.remote.WordDefinitionService
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionMapper
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionRepository
import com.nextvocab.nextvocab.data.repository.wordDefinition.WordDefinitionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDictionaryApiService(): WordDefinitionService {
        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordDefinitionService::class.java)
    }

    @Provides
    fun provideWordDefinitionMapper(): WordDefinitionMapper {
        return WordDefinitionMapper()
    }

    @Provides
    @Singleton
    fun provideWordDefinition(
        wordDefinitionService: WordDefinitionService,
        wordDefinitionMapper: WordDefinitionMapper,
    ): WordDefinitionRepository {
        return WordDefinitionRepositoryImpl(wordDefinitionService, wordDefinitionMapper)
    }
}