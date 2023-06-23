package com.example.recyclerviewwithexploreplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithexploreplayer.data.model.SubMockModel
import com.example.recyclerviewwithexploreplayer.databinding.ItemReviewItemBinding

class SubReviewAdapter : ListAdapter<SubMockModel, RecyclerView.ViewHolder>(DiffUtilCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemReviewItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return SubReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SubReviewViewHolder).bind(getItem(position))
    }

    private inner class SubReviewViewHolder(private val binding: ItemReviewItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item : SubMockModel){
          binding.item = item
        }
    }
}