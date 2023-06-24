package com.example.recyclerviewwithexploreplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewwithexploreplayer.adapter.DataBindingHelper
import com.example.recyclerviewwithexploreplayer.adapter.MainAdapter
import com.example.recyclerviewwithexploreplayer.data.mock.DataProvider
import com.example.recyclerviewwithexploreplayer.data.model.Post
import com.example.recyclerviewwithexploreplayer.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        adapter.submitList(DataProvider.listProvider)
        binding.mainRv.adapter = adapter

        setListener(binding , posts = listOf())
    }

    fun setListener(binding:ActivityMainBinding,posts:List<Post>){
        binding.mainRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val pos = (recyclerView.layoutManager as? LinearLayoutManager)?.findLastCompletelyVisibleItemPosition()

                if (pos == -1) return

                Log.d(TAG, "onScrolled: Scrolled $pos")

                if (posts[pos!!].text == "video" || posts[pos].text == "messageVideo") {
                    // you might remove this line If you only use video players. But, I needed it.
                    posts[pos].playVideo(posts[pos].playerView!!)
                    Log.d(TAG, "onScrolled: played")
                }


                Log.d(TAG, "onScrolled: startCheckPauseTask")
                for (position in posts.indices){
                    if (position == pos) continue

                    posts[position].playerView?.pause()
                    posts[position].playerView?.seekTo(0)
                }
                Log.d(TAG, "onScrolled: finishCheckPauseTask")

            }
        })
    }
}