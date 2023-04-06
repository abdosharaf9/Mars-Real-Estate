package com.abdosharaf.marsrealestate.listScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.marsrealestate.databinding.ItemMarsBinding
import com.abdosharaf.marsrealestate.network.MarsItem

class MarsAdapter: ListAdapter<MarsItem, MarsViewHolder>(MarsDiffUtil) {

    var onItemClicked: ((MarsItem) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        return MarsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClicked)
    }
}

class MarsViewHolder private constructor(private val binding: ItemMarsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(marsItem: MarsItem, clickListener: ((MarsItem) -> Unit)?) {
        binding.marsItem = marsItem
        binding.root.setOnClickListener {
            clickListener?.invoke(marsItem)
        }
    }

    companion object {
        fun from(parent: ViewGroup): MarsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return MarsViewHolder(
                ItemMarsBinding.inflate(layoutInflater, parent, false)
            )
        }
    }
}

object MarsDiffUtil: DiffUtil.ItemCallback<MarsItem>() {
    override fun areItemsTheSame(oldItem: MarsItem, newItem: MarsItem): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: MarsItem, newItem: MarsItem): Boolean {
        return newItem == oldItem
    }
}