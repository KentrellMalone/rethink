package com.kentrell.data.repository

import com.kentrell.data.local.datasource.BlogLocalDataSource
import com.kentrell.data.mapper.CommentMapper
import com.kentrell.data.mapper.PostMapper
import com.kentrell.data.mapper.UserMapper
import com.kentrell.data.remote.datasource.BlogRemoteDataSource
import com.kentrell.domain.extension.mapList
import com.kentrell.domain.model.Comment
import com.kentrell.domain.model.Post
import com.kentrell.domain.model.User
import com.kentrell.domain.repository.BlogRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class BlogRepositoryImpl @Inject constructor(
    private val localDataSource: BlogLocalDataSource,
    private val remoteDataSource: BlogRemoteDataSource,
    private val userMapper: UserMapper,
    private val postMapper: PostMapper,
    private val commentMapper: CommentMapper,
) : BlogRepository {

    override suspend fun getFetchedUsers(): List<User> {
        return remoteDataSource.getUsers().map { userMapper(it) }
    }

    override suspend fun getFetchedPosts(): List<Post> {
        return remoteDataSource.getPosts().map { postMapper(it) }
    }

    override suspend fun getFetchedComments(): List<Comment> {
        return remoteDataSource.getComments().map { commentMapper(it) }
    }

    override fun getUsers(): Flow<List<User>> {
        return localDataSource.getUsers().mapList { userMapper(it) }
    }

    override fun countItems(): Flow<Long> {
        return localDataSource.countItems()
    }

    override suspend fun insertUsers(users: List<User>) {
        localDataSource.insertUsers(users.map { userMapper(it) })
    }

}