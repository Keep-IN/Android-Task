package com.example.brokenapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.brokenapp.databinding.ItemViewBinding

class AdapterItem(private val data: MutableList<ItemModel> = mutableListOf()): RecyclerView.Adapter<AdapterItem.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.validateBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun validateBind(item: ItemModel){
            when(item.type){
                "Keluar" -> {
                    binding.tvTitle.text = item.title
                    binding.tvAccountInfo.text = item.accountDetail
                    binding.tvTransactionAmount.text = item.transactionAmount
                    binding.cardDecrease.isVisible = true
                    binding.cardIncrease.isVisible = false
                }
                "Masuk" -> {
                    binding.tvTitle1.text = item.title
                    binding.tvAccountInfo1.text = item.accountDetail
                    binding.tvTransactionAmount1.text = item.transactionAmount
                    binding.cardDecrease.isVisible = false
                    binding.cardIncrease.isVisible = true
                }
            }
        }
    }
}