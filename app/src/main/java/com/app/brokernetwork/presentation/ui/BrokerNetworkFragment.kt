package com.app.brokernetwork.presentation.ui

import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.window.layout.WindowMetricsCalculator
import com.app.brokernetwork.databinding.FragmentBrokerNetworkBinding
import com.app.brokernetwork.domain.model.BrokerUiEntity
import com.app.brokernetwork.presentation.adapter.BrokerMainAdapter
import com.app.brokernetwork.presentation.base.Resource
import com.app.brokernetwork.presentation.base.SafeObserver
import com.app.brokernetwork.presentation.base.State
import com.app.brokernetwork.presentation.viewmodel.BrokerNetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.window.layout.WindowMetrics as WindowMetrics


@AndroidEntryPoint
class BrokerNetworkFragment : Fragment() {

    private var _binding: FragmentBrokerNetworkBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: BrokerMainAdapter

    private val viewModel by viewModels<BrokerNetworkViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrokerNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBrokerData()
        viewModel.brokerLiveData.observe(
            viewLifecycleOwner,
            SafeObserver(this::handleBrokerResponse)
        )
    }

    private fun handleBrokerResponse(response: Resource<BrokerUiEntity>) {
        println(response.status)
        when (response.status) {
            is State.DataState -> successResponse(response.data)
            is State.ErrorState -> handleErrorResponse(response.throwable)
            is State.LoadingState -> println("Loading")
        }
    }

    private fun successResponse(response: BrokerUiEntity?) {
        response?.let {
            rvAdapter = BrokerMainAdapter(it.cards, requireContext())
            binding.rvMain.adapter = rvAdapter
        }
    }

    private fun handleErrorResponse(throwable: Throwable?) {
        throwable?.let {
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
        }
    }


}