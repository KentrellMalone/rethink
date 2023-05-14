package com.kentrell.domain.usecase

import com.kentrell.domain.base.FlowUseCase
import com.kentrell.domain.repository.BlogRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class CountItemsUseCase @Inject constructor(
    private val repository: BlogRepository,
) : FlowUseCase<Unit, Long>() {

    override fun execute(parameter: Unit): Flow<Long> {
        return repository.countItems()
    }

}