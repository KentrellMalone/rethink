package com.kentrell.domain.repository

import com.kentrell.domain.model.Comment
import com.kentrell.domain.model.Post
import com.kentrell.domain.model.User
import kotlinx.coroutines.flow.Flow

interface BlogRepository {

    suspend fun getFetchedUsers(): List<User>

    suspend fun getFetchedPosts(): List<Post>

    suspend fun getFetchedComments(): List<Comment>

    fun getUsers(): Flow<List<User>>

    fun countItems(): Flow<Long>

    suspend fun insertUsers(users: List<User>)

}