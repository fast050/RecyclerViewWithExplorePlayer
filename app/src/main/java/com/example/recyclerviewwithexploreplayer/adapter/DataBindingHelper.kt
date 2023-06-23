package com.example.recyclerviewwithexploreplayer.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerviewwithexploreplayer.app.GlideApp

object DataBindingHelper {

    @JvmStatic
    @BindingAdapter("imageUrl", "placeHolder")
    fun ImageView.loadImage(url: String?, placeHolder: Drawable?) {
        GlideApp.with(this.context)
            .load(url)
            .placeholder(placeHolder?.let { placeHolder })
            .error(placeHolder?.let { placeHolder })
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .fitCenter()
            .into(this)

    }
}