package com.example.recyclerviewwithexploreplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewwithexploreplayer.adapter.DataBindingHelper
import com.example.recyclerviewwithexploreplayer.adapter.MainAdapter
import com.example.recyclerviewwithexploreplayer.data.mock.DataProvider
import com.example.recyclerviewwithexploreplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MainAdapter()
        adapter.submitList(DataProvider.listProvider)
        binding.mainRv.adapter = adapter
    }
}