package com.kentrell.data.mapper

import com.kentrell.data.local.model.PostEntity
import com.kentrell.data.remote.model.PostResponse
import com.kentrell.domain.model.Post
import io.realm.kotlin.ext.realmListOf
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val commentMapper: CommentMapper,
) {

    operator fun invoke(response: PostResponse): Post {
        return Post(
            id = response.id,
            title = response.title,
            body = response.body,
            userId = response.userId,
        )
    }

    operator fun invoke(entity: PostEntity): Post {
        return Post(
            id = entity.id,
            body = entity.body,
            title = entity.title,
            comments = entity.comments.map { commentMapper(it) },
        )
    }

    operator fun invoke(type: Post): PostEntity {
        return PostEntity().apply {
            id = type.id
            body = type.body
            title = type.title
            comments = realmListOf(*type.comments.map { commentMapper(it) }.toTypedArray())
        }
    }

}