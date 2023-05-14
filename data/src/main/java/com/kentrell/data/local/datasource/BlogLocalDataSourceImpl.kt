package com.kentrell.data.local.datasource

import com.kentrell.data.local.model.CommentEntity
import com.kentrell.data.local.model.PostEntity
import com.kentrell.data.local.model.UserEntity
import io.realm.kotlin.Realm
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class BlogLocalDataSourceImpl @Inject constructor(
    private val realm: Realm,
) : BlogLocalDataSource {

    override suspend fun insertUsers(posts: List<UserEntity>) {
        realm.writeBlocking {
            posts.forEach {
                copyToRealm(it)
            }
        }
    }

    override fun getUsers(): Flow<List<UserEntity>> = realm.query(UserEntity::class).asFlow().map {
        it.list
    }

    override fun countItems(): Flow<Long> = combine(
        realm.query(UserEntity::class).count().asFlow(),
        realm.query(PostEntity::class).count().asFlow(),
        realm.query(CommentEntity::class).count().asFlow(),
    ) { users, posts, comments ->
        users + posts + comments
    }

}