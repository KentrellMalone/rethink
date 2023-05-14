package com.kentrell.ui_blog.blog

import com.kentrell.domain.base.Result
import com.kentrell.domain.model.User
import com.kentrell.ui_common.mvi.State

data class BlogState(
    val status: Result<Unit> = Result.Loading,
    val users: List<User> = emptyList(),
    val itemsCount: Long = 0,
) : State