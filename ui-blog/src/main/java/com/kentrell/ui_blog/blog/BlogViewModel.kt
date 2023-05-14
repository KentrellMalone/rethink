package com.kentrell.ui_blog.blog

import androidx.lifecycle.viewModelScope
import com.kentrell.domain.base.Result
import com.kentrell.domain.base.onError
import com.kentrell.domain.base.onSuccess
import com.kentrell.domain.usecase.CountItemsUseCase
import com.kentrell.domain.usecase.GetUsersUseCase
import com.kentrell.domain.usecase.SyncDataUseCase
import com.kentrell.ui_common.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val syncDataUseCase: SyncDataUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val countItemsUseCase: CountItemsUseCase,
) : BaseViewModel<BlogIntent, BlogState, BlogEffect>(BlogState()) {

    init {
        dispatchIntent(BlogIntent.InitUsers)
        dispatchIntent(BlogIntent.InitItemCount)
        dispatchIntent(BlogIntent.SyncData)
    }

    override suspend fun handleIntent(intent: BlogIntent) {
        when (intent) {
            BlogIntent.InitItemCount -> handleInitItemCount()
            BlogIntent.InitUsers -> handleInitUsers()
            BlogIntent.SyncData -> handleSyncData()
        }
    }

    private suspend fun handleSyncData() {
        syncDataUseCase(Unit)
            .onError {
                updateStatus(Result.Error(it))
            }
            .onSuccess {
                updateStatus(Result.Success(Unit))
            }
    }

    private fun updateStatus(result: Result<Unit>) {
        reduceState {
            copy(status = result)
        }
    }

    private fun handleInitUsers() {
        getUsersUseCase(Unit)
            .onEach {
                reduceState {
                    copy(users = it)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleInitItemCount() {
        countItemsUseCase(Unit)
            .onEach {
                reduceState {
                    copy(itemsCount = it)
                }
            }
            .launchIn(viewModelScope)
    }

}