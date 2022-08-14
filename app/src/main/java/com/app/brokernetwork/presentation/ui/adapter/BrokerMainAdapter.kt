package com.app.brokernetwork.presentation.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.app.brokernetwork.R
import com.app.brokernetwork.databinding.HorizontalItemBinding
import com.app.brokernetwork.databinding.MainItemBinding
import com.app.brokernetwork.domain.model.CardInfo
import com.app.brokernetwork.domain.model.Cards
import com.bumptech.glide.Glide

class BrokerMainAdapter(private val list: List<Cards>, val context: Context) :
    RecyclerView.Adapter<BrokerMainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                with(binding.icContent) {
                    tvTitle.setTextColor(Color.RED)
                    tvPrice.setTextColor(Color.BLACK)
                    tvDescription.setTextColor(Color.BLACK)
                    ivType.setImageResource(R.drawable.account)
                    tvTitle.text = data.mainPost.title
                    tvPrice.text = data.mainPost.subInfo?.get(0)?.text
                    tvDescription.text = data.mainPost.info
                }

                val adapter = HorizontalAdapter(data.horizontalCards, context)
                LinearSnapHelper().attachToRecyclerView(binding.rvHorizontal)
                binding.rvHorizontal.adapter = adapter
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root)
}

class HorizontalAdapter(private val list: List<CardInfo>, private val context: Context) :
    RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {

    class HorizontalViewHolder(val binding: HorizontalItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val binding =
            HorizontalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                val rawBinding = binding.icContent
                with(rawBinding) {
                    tvTitle.text = title
                    tvPrice.text = subInfo?.get(0)?.text
                    tvDescription.text = info
                    ivType.setImageResource(R.drawable.home)
                    DrawableCompat.setTint(
                        DrawableCompat.wrap(ivType.drawable),
                        ContextCompat.getColor(context, R.color.white)
                    )
                }

                assignedTo?.let { assigned ->
                    binding.tvName.text = assigned.name
                    binding.tvOrgName.text = assigned.orgName
                    Glide.with(context).load(assigned.profilePicUrl).circleCrop()
                        .into(binding.ivProfile)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

