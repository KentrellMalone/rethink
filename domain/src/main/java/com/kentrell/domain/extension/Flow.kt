package com.kentrell.domain.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


inline fun <T : Any, R : Any> Flow<List<T>>.mapList(
    crossinline transform: suspend (T) -> R,
) = map { list ->
    list.map { item ->
        transform(item)
    }
}