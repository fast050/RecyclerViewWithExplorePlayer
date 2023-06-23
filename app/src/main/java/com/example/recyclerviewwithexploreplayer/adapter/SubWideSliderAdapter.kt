package com.example.recyclerviewwithexploreplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithexploreplayer.data.model.SubMockModel
import com.example.recyclerviewwithexploreplayer.databinding.ItemWideSliderItemBinding

class SubWideSliderAdapter : ListAdapter<SubMockModel, RecyclerView.ViewHolder>(DiffUtilCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemWideSliderItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeaderViewHolder).bind(getItem(position))
    }

    private inner class HeaderViewHolder(private val binding: ItemWideSliderItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item : SubMockModel){
           binding.item = item
        }
    }
}