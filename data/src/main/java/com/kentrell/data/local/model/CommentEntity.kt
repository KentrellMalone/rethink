package com.kentrell.data.local.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class CommentEntity : RealmObject {
    @PrimaryKey
    var id: Int = 0
    var body: String = ""
    var email: String = ""
    var name: String = ""
}