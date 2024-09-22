package com.nextvocab.nextvocab.data.di

import com.nextvocab.nextvocab.data.remote.DictionaryApiService
import com.nextvocab.nextvocab.data.repository.DictionaryRepositoryImpl
import com.nextvocab.nextvocab.domain.repository.DictionaryRepository
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
    fun provideDictionaryApiService(): DictionaryApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApiService::class.java)
    }

    @Provides
    fun provideDictionaryRepository(
        apiService: DictionaryApiService
    ): DictionaryRepository {
        return DictionaryRepositoryImpl(apiService)
    }
}