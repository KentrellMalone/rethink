package com.kentrell.domain.model

data class User(
    val id: Int,
    val username: String,
    val name: String,
    val email: String,
    val phone: String,
    val posts: List<Post> = emptyList(),
)