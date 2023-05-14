package com.kentrell.data.local.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PostEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var body: String = ""
    var comments: RealmList<CommentEntity> = realmListOf()
}