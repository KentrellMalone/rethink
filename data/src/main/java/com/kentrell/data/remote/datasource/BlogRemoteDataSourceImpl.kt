package com.kentrell.data.remote.datasource

import com.kentrell.data.remote.api.BlogApiService
import com.kentrell.data.remote.model.CommentResponse
import com.kentrell.data.remote.model.PostResponse
import com.kentrell.data.remote.model.UserResponse
import com.kentrell.data.remote.util.bodyOrThrow
import javax.inject.Inject

class BlogRemoteDataSourceImpl @Inject constructor(
    private val apiService: BlogApiService,
) : BlogRemoteDataSource {

    override suspend fun getUsers(): List<UserResponse> = bodyOrThrow {
        apiService.getUsers()
    }

    override suspend fun getPosts(): List<PostResponse> = bodyOrThrow {
        apiService.getPosts()
    }

    override suspend fun getComments(): List<CommentResponse> = bodyOrThrow {
        apiService.getComments()
    }

}