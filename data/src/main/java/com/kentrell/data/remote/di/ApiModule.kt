package com.kentrell.data.remote.di

import com.kentrell.data.remote.api.BlogApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): BlogApiService {
        return retrofit.create(BlogApiService::class.java)
    }

}