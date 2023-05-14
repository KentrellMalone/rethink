package com.kentrell.domain.model

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val comments: List<Comment> = emptyList(),
    val userId: Int = 0,
)