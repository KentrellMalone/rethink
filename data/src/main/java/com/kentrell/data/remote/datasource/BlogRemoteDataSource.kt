package com.kentrell.data.remote.datasource

import com.kentrell.data.remote.model.CommentResponse
import com.kentrell.data.remote.model.PostResponse
import com.kentrell.data.remote.model.UserResponse

interface BlogRemoteDataSource {

    suspend fun getUsers(): List<UserResponse>

    suspend fun getPosts(): List<PostResponse>

    suspend fun getComments(): List<CommentResponse>

}