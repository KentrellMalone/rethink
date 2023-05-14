package com.kentrell.domain.usecase

import com.kentrell.domain.base.FlowUseCase
import com.kentrell.domain.model.User
import com.kentrell.domain.repository.BlogRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase @Inject constructor(
    private val repository: BlogRepository,
) : FlowUseCase<Unit, List<User>>() {

    override fun execute(parameter: Unit): Flow<List<User>> {
        return repository.getUsers()
    }

}