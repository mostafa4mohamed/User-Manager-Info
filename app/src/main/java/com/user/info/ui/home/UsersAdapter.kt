package com.user.info.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.user.domain.entities.User
import com.user.info.databinding.ItemUserBinding
import javax.inject.Inject


class UsersAdapter @Inject constructor() :
    ListAdapter<User, UsersAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, Type: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: User) {

            binding.apply {

                tvNameValue.text = data.name
                tvJobTitleValue.text = data.jobTitle

                root.setOnClickListener {
                    onItemClickListener?.let { click ->
                        click(data)
                    }
                }
            }

            binding.executePendingBindings()
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User, newItem: User
        ): Boolean = newItem == oldItem

        override fun areContentsTheSame(
            oldItem: User, newItem: User
        ): Boolean = newItem == oldItem
    }

    private var onItemClickListener: ((User) -> Unit)? = null

    fun setOnItemClickListener(listener: (User) -> Unit) {
        onItemClickListener = listener
    }

    fun submitData(data: List<User>?) {
        clear()
        submitList(data)
    }

    private fun clear() {
        submitList(emptyList())
    }
}