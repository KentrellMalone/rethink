package com.kentrell.ui_blog.blog

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.kentrell.domain.base.Result
import com.kentrell.domain.model.User
import com.kentrell.domain.usecase.CountItemsUseCase
import com.kentrell.domain.usecase.GetUsersUseCase
import com.kentrell.domain.usecase.SyncDataUseCase
import com.kentrell.ui_blog.util.CoroutinesTestExtension
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutinesTestExtension::class)
internal class BlogViewModelTest {

    @RelaxedMockK
    lateinit var syncDataUseCase: SyncDataUseCase

    @RelaxedMockK
    lateinit var getUsersUseCase: GetUsersUseCase

    @RelaxedMockK
    lateinit var countItemsUseCase: CountItemsUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initialStateErrorSyncShouldUpdateState() = runTest {
        val exception = Exception()
        coEvery { syncDataUseCase(Unit) } returns Result.Error(exception)
        val viewModel = createViewModel()

        viewModel.state.test {
            val state = awaitItem()

            Truth.assertThat(state.status).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((state.status as Result.Error).error).isEqualTo(exception)
        }
    }

    @Test
    fun initialStateSuccessSyncShouldUpdateState() = runTest {
        coEvery { syncDataUseCase(Unit) } returns Result.Success(Unit)
        val viewModel = createViewModel()

        viewModel.state.test {
            val state = awaitItem()

            Truth.assertThat(state.status).isInstanceOf(Result.Success::class.java)
        }
    }

    @Test
    fun initialStateGetItemsCountSyncShouldUpdateState() = runTest {
        val itemsCount = 10L
        coEvery { countItemsUseCase(Unit) } returns flowOf(itemsCount)
        val viewModel = createViewModel()

        viewModel.state.test {
            val state = awaitItem()

            Truth.assertThat(state.itemsCount).isEqualTo(itemsCount)
        }
    }

    @Test
    fun initialStateGetUsersSyncShouldUpdateState() = runTest {
        val user = User(0, "", "", "", "")
        val users = listOf(user)

        coEvery { getUsersUseCase(Unit) } returns flowOf(users)
        val viewModel = createViewModel()

        viewModel.state.test {
            val state = awaitItem()

            Truth.assertThat(state.users).isEqualTo(users)
        }
    }

    private fun createViewModel() = BlogViewModel(
        syncDataUseCase = syncDataUseCase,
        getUsersUseCase = getUsersUseCase,
        countItemsUseCase = countItemsUseCase,
    )

}