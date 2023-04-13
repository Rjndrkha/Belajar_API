package com.dicoding.rjndrkha.latihanapps.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.rjndrkha.latihanapps.R
import com.dicoding.rjndrkha.latihanapps.databinding.ItemRecyleUserBinding
import com.dicoding.rjndrkha.latihanapps.networking.UserResponse

class UserAdapter :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    private val listUserResponse = ArrayList<UserResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            ItemRecyleUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listUserResponse[position])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUserResponse[position]) }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addDataToList(items: ArrayList<UserResponse>) {
        listUserResponse.clear()
        listUserResponse.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listUserResponse.size

    class MyViewHolder(private var binding: ItemRecyleUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userResponse: UserResponse) {
            binding.name.text = userResponse.login
            Glide.with(binding.root)
                .load(userResponse.avatarUrl)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                )
                .into(binding.imgItemPhoto)

        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}

interface OnItemClickCallback {
    fun onItemClicked(user: UserResponse)
}
