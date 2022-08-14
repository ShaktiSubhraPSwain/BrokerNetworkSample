package com.app.brokernetwork.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.brokernetwork.domain.model.BrokerUiEntity
import com.app.brokernetwork.domain.model.NetworkResponse
import com.app.brokernetwork.domain.usecase.BrokerNetworkUseCase
import com.app.brokernetwork.presentation.base.Resource
import com.app.brokernetwork.presentation.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrokerNetworkViewModel @Inject constructor(private val useCase: BrokerNetworkUseCase) : ViewModel(){

    private val _brokerMLD = MutableLiveData<Resource<BrokerUiEntity>>()
    val brokerLiveData: LiveData<Resource<BrokerUiEntity>> = _brokerMLD


    fun getBrokerData() {
        _brokerMLD.value = Resource(State.LoadingState)
        viewModelScope.launch {
            when(val data = useCase.getData()) {
                is NetworkResponse.Success -> handleSuccessResponse(data.body)
                is NetworkResponse.ApiError -> handleFailure(data.body)
                is NetworkResponse.NetworkError -> handleFailure(data.error)
                is NetworkResponse.UnknownError -> handleFailure(data.error)
            }
        }
    }

    private fun handleSuccessResponse(data: BrokerUiEntity) {
        _brokerMLD.value = Resource(data = data, status = State.DataState(data))
    }

    private fun handleFailure(throwable: Throwable?) {
        throwable?.let {
            _brokerMLD.value =
                Resource(throwable = it, status = State.ErrorState(it))
        }
    }
}