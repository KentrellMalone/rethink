package com.kentrell.domain.model

data class Post(
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    val comments: List<Comment> = emptyList(),
)