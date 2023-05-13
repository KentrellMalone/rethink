package com.kentrell.data.remote.di

import com.kentrell.data.remote.datasource.BlogRemoteDataSource
import com.kentrell.data.remote.datasource.BlogRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsBlogRemoteDataSource(impl: BlogRemoteDataSourceImpl): BlogRemoteDataSource

}