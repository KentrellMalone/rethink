package com.kentrell.data.local.datasource

import com.kentrell.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface BlogLocalDataSource {

    suspend fun insertUsers(posts: List<UserEntity>)

    fun getUsers(): Flow<List<UserEntity>>

    fun countItems(): Flow<Long>

}