package com.example.flipcart.model

data class FilterModel(
    var title: String,
    var subarray: List<FilterChildModel>,
    )



data class FilterChildModel(
    var title: String,
    var isCheck: Boolean,
)