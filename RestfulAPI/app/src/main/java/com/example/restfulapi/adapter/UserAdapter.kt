package com.example.restfulapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restfulapi.databinding.ItemViewBinding
import com.example.restfulapi.network.model.User

class UserAdapter(private val data: MutableList<User>? = mutableListOf()): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var itemListener: ((String, String, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { holder.validateBind(it, itemListener) }
    }

    override fun getItemCount(): Int = data?.size!!

    inner class ViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun validateBind(item: User, listener: ((String, String, String) -> Unit)?){
            binding.tvUserName.text = item.firstName
            binding.tvEmail.text = item.email
        }
    }
}

