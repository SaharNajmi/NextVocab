package com.nextvocab.nextvocab.di

import com.nextvocab.nextvocab.data.remote.ApiService
import com.nextvocab.nextvocab.data.mapper.WordDefinitionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDictionaryApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideWordDefinitionMapper(): WordDefinitionMapper {
        return WordDefinitionMapper()
    }

}