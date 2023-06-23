package com.example.recyclerviewwithexploreplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithexploreplayer.data.model.SubMockModel
import com.example.recyclerviewwithexploreplayer.databinding.ItemCategoryItemBinding

class SubCategoryAdapter: ListAdapter<SubMockModel, RecyclerView.ViewHolder>(DiffUtilCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCategoryItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeaderViewHolder).bind(getItem(position))
    }

    private inner class HeaderViewHolder(private val binding: ItemCategoryItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(item : SubMockModel){
           binding.item = item
        }
    }
}