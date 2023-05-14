package com.kentrell.domain.usecase

import com.kentrell.domain.base.IoDispatcher
import com.kentrell.domain.base.Result
import com.kentrell.domain.base.ResultUseCase
import com.kentrell.domain.repository.BlogRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class SyncDataUseCase @Inject constructor(
    private val repository: BlogRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : ResultUseCase<Unit, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: Unit): Result<Unit> {
        val users = repository.getFetchedUsers()
        val posts = repository.getFetchedPosts().toMutableList().groupBy { it.userId }
        val comments = repository.getFetchedComments().toMutableList().groupBy { it.postId }

        users.map { user ->
            val userPosts = posts.getOrDefault(user.id, emptyList()).map { post ->
                post.copy(comments = comments.getOrDefault(post.id, emptyList()))
            }
            user.copy(posts = userPosts)
        }.let {
            repository.insertUsers(it)
        }

        return Result.Success(Unit)
    }

}