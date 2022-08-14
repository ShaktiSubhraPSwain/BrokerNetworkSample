package com.app.brokernetwork.domain.repository

import com.app.brokernetwork.domain.model.BrokerUiEntity
import com.app.brokernetwork.domain.model.NetworkResponse
import java.lang.Error

interface BrokerRepository {

    suspend fun getData(): NetworkResponse<BrokerUiEntity, Error>
}