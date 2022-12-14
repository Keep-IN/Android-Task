package com.example.restfulapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restfulapi.MainActivity
import com.example.restfulapi.databinding.ItemViewBinding
import com.example.restfulapi.network.model.User

class UserAdapter(private val context: Context, private val data: MutableList<User> = mutableListOf()): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var itemListener: ((String, String, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position].let { holder.validateBind(it, itemListener) }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun validateBind(item: User, listener: ((String, String, String) -> Unit)?){
            binding.root.setOnClickListener {
                listener?.invoke(item.firstName, item.email, item.avatar)
            }
            binding.tvUserName.text = item.firstName
            binding.tvEmail.text = item.email
            Glide
                .with(context)
                .load(item.avatar)
                .into(binding.imgUser)
        }
    }
    fun setOnItemClicker(listener: ((String, String, String) -> Unit)?){
        this.itemListener = listener
    }
}

