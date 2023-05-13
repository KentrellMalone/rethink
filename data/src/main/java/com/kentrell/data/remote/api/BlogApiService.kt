package com.kentrell.data.remote.api

import com.kentrell.data.remote.model.CommentResponse
import com.kentrell.data.remote.model.PostResponse
import com.kentrell.data.remote.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface BlogApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("comments")
    suspend fun getComments(): Response<List<CommentResponse>>

}