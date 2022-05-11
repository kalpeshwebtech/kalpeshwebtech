package com.example.flipcart.model

data class ReviewsModel(
    var review: String,
    var title: String,
    var name: String,
    var like:String,
    var dislike:String,
    var desc:String,

    var images:ArrayList<String>
    )