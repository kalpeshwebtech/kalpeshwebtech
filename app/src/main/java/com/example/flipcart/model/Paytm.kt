package com.example.flipcart.model

import java.io.Serializable

data class Paytm (
    val name: String,
    val icon: String,
    val coin_amount: List<CoinAmount> = arrayListOf(),
):Serializable
