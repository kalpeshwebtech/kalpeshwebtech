package com.webecom.model

data class FilterModel(
    var title: String,
    var subarray: List<FilterChildModel>,
    )



data class FilterChildModel(
    var title: String,
    var isCheck: Boolean,
)