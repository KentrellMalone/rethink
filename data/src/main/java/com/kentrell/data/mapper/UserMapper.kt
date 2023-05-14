package com.kentrell.data.mapper

import com.kentrell.data.local.model.UserEntity
import com.kentrell.data.remote.model.UserResponse
import com.kentrell.domain.model.User
import io.realm.kotlin.ext.realmListOf
import javax.inject.Inject

class UserMapper @Inject constructor(
    private val postMapper: PostMapper,
) {

    operator fun invoke(response: UserResponse): User {
        return User(
            id = response.id,
            username = response.username,
            name = response.name,
            email = response.email,
            phone = response.phone,
        )
    }

    operator fun invoke(entity: UserEntity): User {
        return User(
            id = entity.id,
            username = entity.username,
            name = entity.name,
            email = entity.email,
            phone = entity.phone,
            posts = entity.posts.map { postMapper(it) },
        )
    }

    operator fun invoke(type: User): UserEntity {
        return UserEntity().apply {
            id = type.id
            email = type.email
            name = type.name
            phone = type.phone
            username = type.username
            posts = realmListOf(*type.posts.map { postMapper(it) }.toTypedArray())
        }
    }

}