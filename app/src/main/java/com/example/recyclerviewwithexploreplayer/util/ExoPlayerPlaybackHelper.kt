package com.example.recyclerviewwithexploreplayer.util

import android.annotation.SuppressLint
import com.google.android.exoplayer2.ExoPlayer

@SuppressLint("StaticFieldLeak", "Deprecation")
class ExoPlayerPlaybackHelper {
    companion object{
        var playerView : ExoPlayer? = null
    }
}