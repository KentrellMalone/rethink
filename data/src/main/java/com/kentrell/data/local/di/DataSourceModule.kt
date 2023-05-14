package com.kentrell.data.local.di

import com.kentrell.data.local.datasource.BlogLocalDataSource
import com.kentrell.data.local.datasource.BlogLocalDataSourceImpl
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
    abstract fun bindsBlogLocalDataSource(impl: BlogLocalDataSourceImpl): BlogLocalDataSource

}