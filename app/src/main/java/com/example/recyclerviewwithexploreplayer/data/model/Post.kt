package com.example.recyclerviewwithexploreplayer.data.model

import com.example.recyclerviewwithexploreplayer.util.ExoPlayerPlaybackHelper
import com.google.android.exoplayer2.ExoPlayer

data class Post(var text:String?, var playerView: ExoPlayer?){
    fun playVideo(playerView : ExoPlayer) {
        ExoPlayerPlaybackHelper.playerView?.pause()

        ExoPlayerPlaybackHelper.playerView = playerView

        playerView.play()
    }

    constructor() : this(null, null) //passing the ExoPlayer object null for now
}