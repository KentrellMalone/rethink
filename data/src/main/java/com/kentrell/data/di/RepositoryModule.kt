package com.kentrell.data.di

import com.kentrell.data.repository.BlogRepositoryImpl
import com.kentrell.domain.repository.BlogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsBlogRepository(impl: BlogRepositoryImpl): BlogRepository

}