package com.app.brokernetwork.data.repository

import com.app.brokernetwork.data.mapper.toBrokerUiEntity
import com.app.brokernetwork.domain.model.BrokerUiEntity
import com.app.brokernetwork.domain.model.NetworkResponse
import com.app.brokernetwork.domain.repository.BrokerApi
import com.app.brokernetwork.domain.repository.BrokerRepository
import java.lang.Error
import javax.inject.Inject

class BrokerRepositoryImpl @Inject constructor(private val apiService: BrokerApi) :
    BrokerRepository {

    override suspend fun getData(): NetworkResponse<BrokerUiEntity, Error> {
//        val result1 = apiService.getData1()
//        println(result1)
        return when (val result = apiService.getData()) {
            is NetworkResponse.Success -> {
                val data = result.body.toBrokerUiEntity()
                NetworkResponse.Success(data)
            }
            is NetworkResponse.ApiError -> {
                NetworkResponse.ApiError(result.body, result.code)
            }
            is NetworkResponse.NetworkError -> {
                NetworkResponse.NetworkError(result.error)
            }
            is NetworkResponse.UnknownError -> {
                NetworkResponse.UnknownError(result.error)
            }
        }
    }
}
