package com.kentrell.ui_blog.blog

import com.kentrell.ui_common.mvi.Effect

sealed interface BlogEffect : Effect {
    object OnShowFetchError : BlogEffect
}