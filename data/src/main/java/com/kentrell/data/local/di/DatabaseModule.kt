package com.kentrell.data.local.di

import com.kentrell.data.local.model.CommentEntity
import com.kentrell.data.local.model.PostEntity
import com.kentrell.data.local.model.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesRealmDatabase(): Realm {
        val config = RealmConfiguration.create(
            setOf(
                UserEntity::class,
                PostEntity::class,
                CommentEntity::class,
            )
        )
        return Realm.open(config)
    }

}