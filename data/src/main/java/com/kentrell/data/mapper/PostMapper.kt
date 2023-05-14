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
            body = response.body,
            email = response.email,
            name = response.name,
        )
    }

    operator fun invoke(entity: PostEntity): Post {
        return Post(
            id = entity.id,
            body = entity.body,
            email = entity.email,
            name = entity.name,
            comments = entity.comments.map { commentMapper(it) },
        )
    }

    operator fun invoke(type: Post): PostEntity {
        return PostEntity().apply {
            id = type.id
            body = type.body
            email = type.email
            name = type.name
            comments = realmListOf(*type.comments.map { commentMapper(it) }.toTypedArray())
        }
    }

}