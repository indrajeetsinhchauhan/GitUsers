package com.indrajeetsinhchauhan.gitusers.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indrajeetsinhchauhan.gitusers.data.UserModel
import com.indrajeetsinhchauhan.gitusers.databinding.RvRowItemViewBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var data: ArrayList<UserModel>? = null
    private lateinit var binding: RvRowItemViewBinding

    interface UserListener {
    }

    fun setData(list: ArrayList<UserModel>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        binding = RvRowItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data?.get(position)
        binding.tvHomeItemTitle.text = item?.login
        holder.itemView.setOnClickListener {
            val intent = Intent(binding.root.context, UserDetailsActivity::class.java)
            intent.putExtra("user", item?.login)
            binding.root.context.startActivity(intent)
        }
    }

    class UserViewHolder(binding: RvRowItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}