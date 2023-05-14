package com.kentrell.ui_blog.blog

import com.kentrell.ui_common.mvi.Intent

sealed interface BlogIntent : Intent {
    object InitUsers : BlogIntent
    object InitItemCount : BlogIntent
    object SyncData : BlogIntent
}