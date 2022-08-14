package com.app.brokernetwork.domain.repository

import android.telecom.Call
import com.app.brokernetwork.data.model.BrokerApiModel
import com.app.brokernetwork.domain.model.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import java.lang.Error

interface BrokerApi {
    @GET("c52cf4ce-a639-42d7-a606-2c0a8b848536")
    suspend fun getData() : NetworkResponse<BrokerApiModel, Error>
//
//    @GET("c52cf4ce-a639-42d7-a606-2c0a8b848536")
//    suspend fun getData1() : Call<Map<String, String>, Error>
}