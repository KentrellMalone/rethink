package com.kentrell.data.mapper

import com.kentrell.data.local.model.CommentEntity
import com.kentrell.data.remote.model.CommentResponse
import com.kentrell.domain.model.Comment
import javax.inject.Inject

class CommentMapper @Inject constructor() {

    operator fun invoke(response: CommentResponse): Comment {
        return Comment(
            id = response.id,
            body = response.body,
            email = response.email,
            name = response.name,
            postId = response.postId,
        )
    }

    operator fun invoke(entity: CommentEntity): Comment {
        return Comment(
            id = entity.id,
            body = entity.body,
            email = entity.email,
            name = entity.name,
        )
    }

    operator fun invoke(type: Comment): CommentEntity {
        return CommentEntity().apply {
            id = type.id
            body = type.body
            email = type.email
            name = type.name
        }
    }

}