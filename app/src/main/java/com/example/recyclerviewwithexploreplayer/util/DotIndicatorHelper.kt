package com.example.recyclerviewwithexploreplayer.util

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithexploreplayer.R

/**
 *  use this class when ever u need generic dot indicator with the recycler view
 */

class DotIndicatorHelper<T>(
    private val context: Context,
    private val list: List<T>,
    private val linearLayout: LinearLayout,
    private val recyclerView: RecyclerView,
    private val layoutManager: LinearLayoutManager
) {

    //Reuse existing dot views: Instead of recreating the dot views every time
    private val dotViews = mutableListOf<ImageView>()

    //for the dot shape in dot indicator , for unselected places
    private val circleDrawable = ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.dot_shape_gray,
        context.theme
    )

    //for the rectangle shape in dot indicator , for selected place
    private val rectangleDrawable = ResourcesCompat.getDrawable(
        context.resources,
        R.drawable.dot_shape_black,
        context.theme
    )

    //for determine the distance between each dots , the selected and the unselected
    private val distanceBetweenDots = 15

    init {
        observeRecyclerViewScroll()
        addDot()
        drawIndicatorOfScrollPositionDynamic( 0)
    }


    /**
     *  to draw dot in the screen , by add it to linearLayout at the run time
    ,   the number of the dots will be determine by the list size.
     */

    private fun addDot() {
        // here am check how much the item and add the dot at run time
        for (item in list) {
            //create the dot image view
            val imageView = ImageView(context).apply {
                this.setImageDrawable(circleDrawable)

                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                //add space between dots because we start add dot from left then after each dot add
                //we add space on the right
                layoutParams.setMargins(0, 0, distanceBetweenDots, 0)
                this.layoutParams = layoutParams
            }

            linearLayout.addView(imageView)
            dotViews.add(imageView)
        }

    }


    /**
     *  to observe the change when the user scroll ,
    each item the use scroll it call drawIndicatorOfScrollPositionDynamic()
     */

    private fun observeRecyclerViewScroll() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (layoutManager.findFirstVisibleItemPosition() >= 0) {

                    Log.d(
                        "DotIndicatorHelper",
                        "onScrolled: observeRecyclerViewScroll inside ${layoutManager.findFirstCompletelyVisibleItemPosition()}"
                    )

                    drawIndicatorOfScrollPositionDynamic(
                        layoutManager.findFirstVisibleItemPosition() % list.size
                    )
                }

            }
        })
    }


    /**
     *
     *   to draw line at the current position scroll by user,

    this function is draw line at the current position in the recycler view and draw the dot
    else (to create the effect of the moving line with the dot)
     */
    private fun drawIndicatorOfScrollPositionDynamic(position: Int) {

        initIndicatorDotDrawing()

        val view = linearLayout.getChildAt(position) as ImageView

        view.setImageDrawable(rectangleDrawable)

        view.updateLayoutParams<LinearLayout.LayoutParams> {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height= ViewGroup.LayoutParams.WRAP_CONTENT
            setMargins(0, 0, distanceBetweenDots, 0)
        }
    }


    /**
     * to draw dot at each place
     */

    private fun initIndicatorDotDrawing() {

        for (i in 0 until dotViews.size) {

            val view = dotViews[i]

            view.setImageDrawable(circleDrawable)

            view.updateLayoutParams<LinearLayout.LayoutParams> {
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height= ViewGroup.LayoutParams.WRAP_CONTENT
                setMargins(0, 0, distanceBetweenDots, 0)
            }
        }
    }

}
