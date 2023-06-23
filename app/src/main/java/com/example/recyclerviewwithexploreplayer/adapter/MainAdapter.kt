package com.example.recyclerviewwithexploreplayer.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithexploreplayer.data.Enum.DataSectionType
import com.example.recyclerviewwithexploreplayer.data.model.MockModel
import com.example.recyclerviewwithexploreplayer.data.model.SubMockModel
import com.example.recyclerviewwithexploreplayer.databinding.ItemCategoryBinding
import com.example.recyclerviewwithexploreplayer.databinding.ItemHeaderBinding
import com.example.recyclerviewwithexploreplayer.databinding.ItemReviewBinding
import com.example.recyclerviewwithexploreplayer.databinding.ItemWideSliderBinding
import com.example.recyclerviewwithexploreplayer.util.AutoScrollHelper
import com.example.recyclerviewwithexploreplayer.util.DotIndicatorHelper

private const val TAG = "MainAdapter"
class MainAdapter : ListAdapter<MockModel, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)

        Log.d(TAG, "onViewAttachedToWindow: $holder")
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)

        Log.d(TAG, "onViewDetachedFromWindow: $holder")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            DataSectionType.Header.value -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HeaderViewHolder(binding)
            }
            DataSectionType.Category.value -> {
                val binding =
                    ItemCategoryBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                CategoryViewHolder(binding)
            }
            DataSectionType.Review.value -> {
                val binding = ItemReviewBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ReviewViewHolder(binding)
            }
            DataSectionType.WideSlider.value -> {
                val binding = ItemWideSliderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                WideSliderViewHolder(binding)
            }
            else -> {
                throw java.lang.IllegalStateException("This Project does not handle that type , Please")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AdapterOnBind<MockModel>).onBind(getItem(position))
    }

    private inner class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root), AdapterOnBind<MockModel> {

        override fun onBind(item: MockModel) {
            binding.item = item
        }

        fun setRecyclerView(item: MockModel, recyclerView: RecyclerView) {
            val adapter = SubCategoryAdapter()
            adapter.submitList(item.list)
            recyclerView.adapter = adapter
        }
    }

    private inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root), AdapterOnBind<MockModel> {

        override fun onBind(item: MockModel) {
            binding.item = item
            setRecyclerView(item, binding.categoryRv)

        }

        fun setRecyclerView(item: MockModel, recyclerView: RecyclerView) {
            val adapter = SubCategoryAdapter()
            adapter.submitList(item.list)
            recyclerView.adapter = adapter
        }
    }

    private inner class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root), AdapterOnBind<MockModel> {

         private val context = binding.root.context
         private var list :List<SubMockModel>? = null

        override fun onBind(item: MockModel) {
            binding.item = item
            setRecyclerView(item, binding.reviewRv)

            list = item.list
        }

        fun setRecyclerView(item: MockModel, recyclerView: RecyclerView) {
            val adapter = SubReviewAdapter()
            val layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
            recyclerView.apply {
                this.layoutManager = layoutManager
                this.adapter = adapter
            }
            adapter.submitList(item.list)

            val listPass = list ?: return

            DotIndicatorHelper(
               context = context,
               list =listPass ,
               linearLayout = binding.indicatorInclude.indicatorContainer,
               recyclerView = recyclerView,
               layoutManager = layoutManager
           )


            AutoScrollHelper(
               adapter =  adapter,
               list = listPass,
               recyclerView = recyclerView,
               scrollDelay = 2000L
            )
        }
    }

    private inner class WideSliderViewHolder(private val binding: ItemWideSliderBinding) :
        RecyclerView.ViewHolder(binding.root), AdapterOnBind<MockModel> {
        private val context = binding.root.context
        private var list :List<SubMockModel>? = null

        override fun onBind(item: MockModel) {
            binding.item = item
            setRecyclerView(item, binding.wideSliderRv)
        }

        fun setRecyclerView(item: MockModel, recyclerView: RecyclerView) {
            val adapter = SubWideSliderAdapter()
            val layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
            recyclerView.apply {
                this.layoutManager = layoutManager
                this.adapter = adapter
            }

            val listPass = list ?: return

            adapter.submitList(listPass)

            DotIndicatorHelper(
                context = context,
                list = listPass,
                linearLayout = binding.indicatorInclude.indicatorContainer,
                recyclerView = recyclerView,
                layoutManager = layoutManager
            )


            AutoScrollHelper(
                adapter =  adapter,
                list = listPass,
                recyclerView = recyclerView,
                scrollDelay = 2000L
            )
        }
    }
}