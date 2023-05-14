package com.kentrell.data.local.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var email: String = ""
    var name: String = ""
    var phone: String = ""
    var username: String = ""
    var posts: RealmList<PostEntity> = realmListOf()
}