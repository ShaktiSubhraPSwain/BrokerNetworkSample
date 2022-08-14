package com.app.brokernetwork.domain.usecase

import com.app.brokernetwork.di.IODispatcher
import com.app.brokernetwork.domain.model.BrokerUiEntity
import com.app.brokernetwork.domain.model.NetworkResponse
import com.app.brokernetwork.domain.repository.BrokerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Error
import javax.inject.Inject


class BrokerNetworkUseCase @Inject constructor(private val repository: BrokerRepository, @IODispatcher private val dispatcher: CoroutineDispatcher) {

    suspend fun getData() : NetworkResponse<BrokerUiEntity, Error> = withContext(dispatcher) {
        repository.getData()
    }
}