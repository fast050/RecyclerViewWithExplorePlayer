package com.example.recyclerviewwithexploreplayer.adapter

interface AdapterOnBind<T> {
    fun onBind(item : T)
}