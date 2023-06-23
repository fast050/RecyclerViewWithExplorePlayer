package com.example.recyclerviewwithexploreplayer.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

/**
 *  use this class to implement infinite auto scroll with delay.
 *
 *  it should be use with linear layout manager recycler view
 */

class AutoScrollHelper<T>(
    private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>,
    private val list: List<T>,
    private val recyclerView: RecyclerView,
    private val scrollDelay: Long
) {
    private var autoScrollCoroutineScope: CoroutineScope? = null
    private var currentJob: Job? = null
    private var scrollListener : RecyclerView.OnScrollListener? = null
    //this to stop the recyclerView onScroll observer if is not used in the same screen
    private var isFragmentVisible = false

    init {
        startAutoScroll()
        isFragmentVisible = true
    }

    /**
     *  this function is to start the auto scrolling
     */
    private fun startAutoScroll() {
        // If there are fewer than 2 items, auto-scrolling is not needed
        if (adapter.itemCount < 2) {
            return
        }

        // Scroll listener to pause and resume auto-scrolling based on scroll state
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                // Pause auto-scrolling when dragged by the user
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    currentJob?.cancel()
                }
                // Resume auto-scrolling when scroll state becomes idle or settling
                else if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    if (currentJob?.isActive != true && isFragmentVisible) {
                        currentJob = startAutoScrollCoroutine(scrollDelay, list.size, recyclerView)
                    }
                }
            }
        }

        recyclerView.addOnScrollListener(scrollListener as RecyclerView.OnScrollListener)
        autoScrollCoroutineScope = CoroutineScope(Dispatchers.Main)
        currentJob = startAutoScrollCoroutine(scrollDelay, list.size, recyclerView)
    }


    /**
     *  this should be called when return to screen that autoScroll used , but for the first time
     *  this class is used it will not work , the startAutoScroll() Function will be called
     */
    fun forceStartAutoScroll(){
        // if it first time this function should not be start
        if (autoScrollCoroutineScope ==null || currentJob == null || scrollListener == null)
            return

        isFragmentVisible = true
        startAutoScroll()
    }

    /**
     *  this should be called when leaving the current screen that auto scrolling used
     */
    fun forceStopAutoScroll() {
        autoScrollCoroutineScope?.cancel()
        currentJob?.cancel()
        recyclerView.removeOnScrollListener(scrollListener as RecyclerView.OnScrollListener)
        isFragmentVisible = false
    }

    private fun startAutoScrollCoroutine(
        scrollDelay: Long,
        listSize: Int,
        recyclerView: RecyclerView
    ): Job? = autoScrollCoroutineScope?.launch {
        while (isActive) {
            delay(scrollDelay)
            val currentPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            // If the scroll happens at the last item, return to the first item; otherwise, go to the next position
            val nextPosition = if ((currentPosition + 1) % listSize == 0) 0 else currentPosition + 1
            recyclerView.smoothScrollToPosition(nextPosition)
        }
    }
}