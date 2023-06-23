package com.example.recyclerviewwithexploreplayer.data.model

data class MockModel (
    val title :String,
    val imageUrl:String,
    val description :String,
    val list: List<SubMockModel>,
    val type : Int
)